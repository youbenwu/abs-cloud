package com.outmao.ebs.wallet.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.eventBus.annotation.ActionEvent;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.common.util.ServletRequestUtil;
import com.outmao.ebs.wallet.common.constant.*;
import com.outmao.ebs.wallet.common.event.WalletTradeEvent;
import com.outmao.ebs.wallet.common.exception.TradeNoFoundException;
import com.outmao.ebs.wallet.common.exception.TradeStatusException;
import com.outmao.ebs.wallet.common.exception.WalletException;
import com.outmao.ebs.wallet.common.exception.WalletStatusException;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dao.CurrencyDao;
import com.outmao.ebs.wallet.dao.TradeDao;
import com.outmao.ebs.wallet.dao.WalletDao;
import com.outmao.ebs.wallet.domain.TradeDomain;
import com.outmao.ebs.wallet.domain.TransferDomain;
import com.outmao.ebs.wallet.domain.conver.TradeVOConver;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.QTrade;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.entity.Wallet;
import com.outmao.ebs.wallet.vo.TradeVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class TradeDomainImpl extends BaseDomain implements TradeDomain {


    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private TransferDomain transferDomain;

    @Autowired
    private TradeVOConver tradeVOConver;


    private List<TradeStatusListener> listeners=new ArrayList<>();

    private void fireListener(Trade trade){
        for (TradeStatusListener l:listeners) {
            try{
                l.statusChanged(trade);
            }catch (Exception e){
                e.printStackTrace();
                throw new BusinessException("系统繁忙，请稍候再试");
            }
        }
    }

    @Override
    public void addStatusListener(TradeStatusListener listener) {
        listeners.add(listener);
    }

    @Transactional
    @Override
    public Trade tradePrepare(TradePrepareDTO request) {

        Trade trade=request.getTradeNo()==null?null:tradeDao.findByTradeNoLock(request.getTradeNo());

        if(trade==null){
            trade=new Trade();
        }else{
            if(trade.getStatus()!= TradeStatus.TRADE_WAIT_PAY.getStatus()){
                throw new TradeStatusException();
            }
        }

        if(request.getTradeNo()==null){
            request.setTradeNo(OrderNoUtil.generateOrderNo());
        }

        Wallet from=request.getFromId()==null?null:walletDao.getOne(request.getFromId());

        Wallet to=request.getToId()==null?null:walletDao.getOne(request.getToId());

        Currency currency=currencyDao.getOne(request.getCurrencyId());

        trade.setFrom(from);
        trade.setTo(to);
        trade.setCurrency(currency);
        trade.setTotalAmount(request.getAmount()+request.getFee());

        trade.setCreateTime(new Date());
        trade.setStatus(TradeStatus.TRADE_WAIT_PAY.getStatus());
        trade.setStatusRemark(TradeStatus.TRADE_WAIT_PAY.getStatusRemark());

        BeanUtils.copyProperties(request,trade);

        checkTrade(trade);

        tradeDao.save(trade);

        return trade;
    }


    //检查交易是否能进行
    private void checkTrade(Trade trade){
        //检查钱包状态
        if(trade.getFrom()!=null&&trade.getFrom().getStatus()!= WalletStatus.WALLET_STATUS_NORMAL.getStatus()){
            throw new WalletStatusException();
        }

        if(trade.getTo()!=null&&trade.getTo().getStatus()!=WalletStatus.WALLET_STATUS_NORMAL.getStatus()){
            throw new WalletStatusException();
        }
    }


    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradePay(String tradeNo) {

        Trade trade = tradeDao.findByTradeNoLock(tradeNo);
        return tradePay(trade);
    }

    private Trade tradePay(Trade trade) {

        if(trade==null){
            throw new TradeNoFoundException();
        }

        if (trade.getStatus() == TradeStatus.TRADE_SUCCEED.getStatus()) {
            //已经支付成功
            return trade;
        }

        if (trade.getStatus() != TradeStatus.TRADE_WAIT_PAY.getStatus()) {
            //状态异常
            throw new TradeStatusException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        if (trade.getType() == TradeType.Transfer.getType()) {

            transferDomain.transferBalance(trade);
            transferDomain.transferFee(trade);
            trade.setFinishTime(new Date());
            trade.setStatus(TradeStatus.TRADE_FINISHED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_FINISHED.getStatusRemark());

        } else if (trade.getType() == TradeType.Pay.getType()) {

            transferDomain.transferAdvance(trade);
            trade.setSuccessTime(new Date());
            trade.setStatus(TradeStatus.TRADE_SUCCEED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_SUCCEED.getStatusRemark());

        }else if (trade.getType() == TradeType.Recharge.getType()) {

            //充值
            transferDomain.transferBalance(trade);
            trade.setFinishTime(new Date());
            trade.setStatus(TradeStatus.TRADE_FINISHED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_FINISHED.getStatusRemark());

        }else if (trade.getType() == TradeType.Cash.getType()) {

            //提现支付
            transferDomain.transferAdvance(trade);
            //transferDomain.transferFee(trade);
            trade.setSuccessTime(new Date());
            trade.setStatus(TradeStatus.TRADE_SUCCEED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_SUCCEED.getStatusRemark());

        }

        tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }



    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradePayTo(TradePayToDTO request) {

        Trade trade = tradeDao.findByTradeNoLock(request.getTradeNo());

        if(trade==null){
            throw new TradeNoFoundException();
        }

        if (trade.getStatus() != TradeStatus.TRADE_SUCCEED.getStatus()) {
            throw new TradeStatusException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        Wallet to=walletDao.getOne(request.getToId());


        transferDomain.transferTo(
                trade,
                to,
                request.getAmount(),
                request.getBusinessType(),
                request.getBusiness(),
                request.getRemark()
        );

        if(trade.getPayAmount()+trade.getRefundAmount()>=trade.getAmount()){

            transferDomain.transferFee(trade);

            //支付完成
            trade.setFinishTime(new Date());
            trade.setStatus(TradeStatus.TRADE_FINISHED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_FINISHED.getStatusRemark());
        }

        tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }

    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradeFinish(String tradeNo) {
        Trade trade =tradeDao.findByTradeNoLock(tradeNo);
        if(trade==null){
            throw new TradeNoFoundException();
        }
        if (trade.getStatus() == TradeStatus.TRADE_FINISHED.getStatus()) {
            return trade;
        }
        if (trade.getStatus() != TradeStatus.TRADE_SUCCEED.getStatus()) {
            throw new TradeStatusException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        transferDomain.transferTo(trade);
        transferDomain.transferFee(trade);

        trade.setFinishTime(new Date());
        trade.setStatus(TradeStatus.TRADE_FINISHED.getStatus());
        trade.setStatusRemark(TradeStatus.TRADE_FINISHED.getStatusRemark());
        tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }

    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradeRefund(TradeRefundDTO request) {

        Trade trade =tradeDao.findByTradeNoLock(request.getTradeNo());

        if(trade==null){
            throw new TradeNoFoundException();
        }

        if (trade.getStatus() != TradeStatus.TRADE_SUCCEED.getStatus()) {
            throw new TradeStatusException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        long refund=request.getAmount();
        long maxRefund=trade.getAmount()-trade.getPayAmount()-trade.getRefundAmount();
        if(refund<maxRefund){
            throw  new WalletException("退款金额超过最大可退金额");
        }

        if(refund+trade.getRefundAmount()>=trade.getAmount()){
            //全额退款加上手续费
            refund=refund+trade.getFee();
        }

        //原路返回
        transferDomain.transferRefund(
                trade,
                refund,
                request.getBusinessType(),
                request.getBusiness(),
                request.getRemark()
        );


        if(trade.getRefundAmount()>=trade.getAmount()){
            //全额退款关闭交易
            trade.setCloseTime(new Date());
            trade.setStatus(TradeStatus.TRADE_CLOSED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_CLOSED.getStatusRemark());
        }else if(trade.getPayAmount()+trade.getRefundAmount()>=trade.getAmount()){
            transferDomain.transferFee(trade);
            trade.setFinishTime(new Date());
            trade.setStatus(TradeStatus.TRADE_FINISHED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_FINISHED.getStatusRemark());
        }

        tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }

    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradeClose(String tradeNo) {

        Trade trade = tradeDao.findByTradeNoLock(tradeNo);

        if(trade==null){
            throw new TradeNoFoundException();
        }
        if (trade.getStatus() == TradeStatus.TRADE_CLOSED.getStatus()) {
            return trade;
        }

        if (trade.getStatus() != TradeStatus.TRADE_WAIT_PAY.getStatus()) {
            throw new TradeStatusException();
        }

        trade.setCloseTime(new Date());
        trade.setStatus(TradeStatus.TRADE_CLOSED.getStatus());
        trade.setStatusRemark("交易关闭");

        trade = tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }


    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradeRecharge(TradeRechargeDTO request) {

        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setToId(request.getWalletId());
        dto.setAmount(request.getAmount());
        dto.setCurrencyId(request.getCurrencyId());
        dto.setPayChannel(PayChannel.WalletPay.getType());
        dto.setType(TradeType.Recharge.getType());
        dto.setBusiness("充值");
        dto.setBusinessType(WalletConstant.business_type_recharge);
        Trade trade=tradePrepare(dto);

        trade=tradePay(trade);

        return trade;
    }



    @Override
    public Trade getTradeByTradeNo(String tradeNo) {
        return tradeDao.findByTradeNo(tradeNo);
    }

    @Override
    public TradeVO getTradeVOById(Long id) {
        QTrade e=QTrade.trade;

        TradeVO vo=queryOne(e,e.id.eq(id),tradeVOConver);

        return vo;
    }

    @Override
    public TradeVO getTradeVOByTradeNo(String tradeNo) {
        QTrade e=QTrade.trade;

        TradeVO vo=queryOne(e,e.tradeNo.eq(tradeNo),tradeVOConver);

        return vo;
    }

    @Override
    public Page<TradeVO> getTradeVOPage(GetTradeListDTO request, Pageable pageable) {
        QTrade e=QTrade.trade;

        Predicate p=null;

        if(request.getWalletId()!=null){
            p=e.from.id.eq(request.getWalletId()).or(e.to.id.eq(request.getWalletId()));
        }

        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn()).and(p);
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        Page<TradeVO> page=queryPage(e,p,tradeVOConver,pageable);

        return page;
    }



}
