package com.outmao.ebs.wallet.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.eventBus.annotation.ActionEvent;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.common.util.ServletRequestUtil;
import com.outmao.ebs.common.vo.Duration;
import com.outmao.ebs.wallet.common.constant.*;
import com.outmao.ebs.wallet.common.event.WalletTradeEvent;
import com.outmao.ebs.wallet.common.exception.TradeNoFoundException;
import com.outmao.ebs.wallet.common.exception.TradeStatusException;
import com.outmao.ebs.wallet.common.exception.WalletException;
import com.outmao.ebs.wallet.common.exception.WalletStatusException;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.common.util.TradeUtil;
import com.outmao.ebs.wallet.dao.*;
import com.outmao.ebs.wallet.domain.TradeDomain;
import com.outmao.ebs.wallet.domain.TransferDomain;
import com.outmao.ebs.wallet.domain.conver.TradeVOConver;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.*;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.vo.TradeVO;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Component
public class TradeDomainImpl extends BaseDomain implements TradeDomain {


    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private TradeProfitSharingDao tradeProfitSharingDao;

    @Autowired
    private TradeProfitSharingReceiverDao tradeProfitSharingReceiverDao;

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
                log.error("交易状态修改出错",e);
                throw new BusinessException("服务器繁忙，请稍候再试");
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

        BeanUtils.copyProperties(request,trade);

        trade.setFrom(from);
        trade.setTo(to);
        trade.setCurrency(currency);
        trade.setTotalAmount(trade.getAmount()+trade.getFee());
        trade.setPayerAmount(trade.getTotalAmount());

        if(request.getFreezeExpress()!=null){
            Duration duration=Duration.parse(request.getFreezeExpress());
            if(duration!=null) {
                trade.setFreeze(duration);
            }else{
                trade.setFreezeExpireTime(DateUtil.parse(request.getFreezeExpress()));
            }
        }

        trade.setTimeoutTime(getFromExpress(request.getTimeoutExpress(),DateUtil.addDays(new Date(),7)));

        if(request.getFinishTimeoutExpress()!=null){
            Duration duration=Duration.parse(request.getFinishTimeoutExpress());
            trade.setFinishTimeout(duration);
        }

        if(trade.getFinishTimeout()==null){
            Duration duration=new Duration(Duration.d,30);
            trade.setFinishTimeout(duration);
        }

        trade.setStatus(TradeStatus.TRADE_WAIT_PAY.getStatus());
        trade.setStatusRemark(TradeStatus.TRADE_WAIT_PAY.getStatusRemark());

        trade.setCreateTime(new Date());

        check(trade);

        tradeDao.save(trade);

        return trade;
    }

    private Date getFromExpress(String s,Date def){
        if(s==null)
            return def;
        Duration duration=Duration.parse(s);
        Date date;
        if(duration!=null){
            date= duration.getToTime(new Date());
        }else{
            date= DateUtil.parse(s);
        }
        if(date==null)
            return def;
        return date;
    }

    //检查交易是否能进行
    private void check(Trade trade){
        //检查钱包状态
        if(trade.getFrom()!=null&&WalletStatus.isNotNormal(trade.getFrom().getStatus())){
            throw new WalletStatusException();
        }

        if(trade.getTo()!=null&&WalletStatus.isNotNormal(trade.getTo().getStatus())){
            throw new WalletStatusException();
        }
    }


    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradePay(TradePayDTO request) {
        Trade trade = tradeDao.findByTradeNoLock(request.getTradeNo());
        return tradePay(trade,request);
    }


    private Trade tradePay(Trade trade,TradePayDTO request) {

        if(trade==null){
            throw new TradeNoFoundException();
        }

        if (TradeUtil.isSucceed(trade)) {
            //已经支付成功
            return trade;
        }

        if (TradeUtil.isNotWaitPay(trade)) {
            //交易状态异常
            throw new TradeStatusException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        BeanUtils.copyProperties(request,trade);

        if (trade.getType() == TradeType.Transfer.getType()) {

            transferDomain.transferBalance(trade);
            transferDomain.transferFee(trade);
            trade.setSuccessTime(new Date());
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
            trade.setSuccessTime(new Date());
            trade.setFinishTime(new Date());
            trade.setStatus(TradeStatus.TRADE_FINISHED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_FINISHED.getStatusRemark());

        }else if (trade.getType() == TradeType.Cash.getType()) {

            //提现支付
            transferDomain.transferAdvance(trade);
            trade.setSuccessTime(new Date());
            trade.setStatus(TradeStatus.TRADE_SUCCEED.getStatus());
            trade.setStatusRemark(TradeStatus.TRADE_SUCCEED.getStatusRemark());

        }

        if(TradeUtil.isSucceed(trade)&&trade.getFinishTimeout()!=null){
            //设置自动完成时间
            trade.setFinishTimeoutTime(trade.getFinishTimeout().getToTime(new Date()));
        }

        tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }



    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradePayee(TradePayeeDTO request) {

        Trade trade = tradeDao.findByTradeNoLock(request.getTradeNo());

        if(trade==null){
            throw new TradeNoFoundException();
        }

        if (!TradeUtil.isSucceed(trade)) {
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

        if(trade.getPayeeAmount()+trade.getRefundAmount()>=trade.getAmount()){
            //收取手续费
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
        if (TradeUtil.isFinished(trade)) {
            return trade;
        }
        if (!TradeUtil.isSucceed(trade)) {
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

        if (!TradeUtil.isSucceed(trade)) {
            throw new TradeStatusException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        long refund=request.getAmount();
        long maxRefund=trade.getAmount()-trade.getPayeeAmount()-trade.getRefundAmount();
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
        }else if(trade.getPayeeAmount()+trade.getRefundAmount()>=trade.getAmount()){
            //订单完成
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
        if (TradeUtil.isClosed(trade)) {
            return trade;
        }

        if (!TradeUtil.isWaitPay(trade)) {
            throw new TradeStatusException();
        }

        trade.setCloseTime(new Date());
        trade.setStatus(TradeStatus.TRADE_CLOSED.getStatus());
        trade.setStatusRemark(TradeStatus.TRADE_CLOSED.getStatusRemark());

        trade = tradeDao.save(trade);

        fireListener(trade);

        return trade;
    }

    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public Trade tradeUnfreeze(String tradeNo) {
        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());
        Trade trade = tradeDao.findByTradeNoLock(tradeNo);
        return tradeUnfreeze(trade);
    }

    private Trade tradeUnfreeze(Trade trade ) {

        if(trade==null){
            throw new TradeNoFoundException();
        }
        if (trade.getFreezeStatus()==3) {
            //已解冻
            return trade;
        }

        if(trade.getFreezeAmount()<=0) {
            return trade;
        }

        if (!TradeUtil.isFinished(trade)) {
            throw new TradeStatusException();
        }

        if(trade.getFreezeStatus()!=1){
            throw new TradeStatusException();
        }

        transferDomain.transferUnfreeze(trade);

        trade = tradeDao.save(trade);

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
        dto.setBusiness(StringUtils.isEmpty(request.getBusiness())?request.getRemark():request.getBusiness());
        dto.setBusinessType(request.getBusinessType()==0?WalletConstant.business_type_recharge:request.getBusinessType());
        dto.setRemark(request.getRemark());

        Trade trade=tradePrepare(dto);

        TradePayDTO payDTO=new TradePayDTO();
        payDTO.setTradeNo(trade.getTradeNo());
        payDTO.setPayChannel(trade.getPayChannel());
        payDTO.setOutPayType(trade.getOutPayType());
        payDTO.setReceiptAmount(trade.getTotalAmount());
        trade=tradePay(trade,payDTO);

        return trade;
    }


    @ActionEvent(WalletTradeEvent.class)
    @Transactional
    @Override
    public TradeProfitSharing tradeProfitSharing(String sharingNo) {
        TradeProfitSharing sharing=tradeProfitSharingDao.findBySharingNoLock(sharingNo);
        if(sharing==null)
            return null;

        if(sharing.getStatus()==2)
            return sharing;

        List<TradeProfitSharingReceiver> receivers=tradeProfitSharingReceiverDao.findAllBySharingId(sharing.getId());

        Trade trade=tradeDao.findByTradeNoLock(sharing.getTradeNo());

        if(trade==null){
            throw new TradeNoFoundException();
        }

        ServletRequestUtil.setAttribute(WalletConstant.action_key, UUID.randomUUID().toString());

        transferDomain.transferProfitSharing(trade,receivers);

        sharing.setStatus(2);
        sharing.setUpdateTime(new Date());
        tradeProfitSharingDao.save(sharing);

        tradeDao.save(trade);

        if(sharing.isUnfreeze()){
            tradeUnfreeze(trade);
        }

        return sharing;
    }

    @Async
    @Override
    public TradeProfitSharing tradeProfitSharingAsync(String sharingNo) {
        return tradeProfitSharing(sharingNo);
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

    @Override
    public Collection<String> getAllTimeoutTradeNo() {
        return tradeDao.findAllTradeNoByTimeoutTimeBefore(new Date());
    }

    @Override
    public Collection<String> getAllFinishTimeoutTradeNo() {
        return tradeDao.findAllTradeNoByFinishTimeoutTimeBefore(new Date());
    }

    @Override
    public Collection<String> getAllFreezeExpireTradeNo() {
        return tradeDao.findAllTradeNoByFreezeExpireTimeBefore(new Date());
    }


    @Transactional
    @Override
    public TradeProfitSharing saveTradeProfitSharing(TradeProfitSharingDTO request) {
        TradeProfitSharing sharing=tradeProfitSharingDao.findBySharingNoLock(request.getSharingNo());
        if(sharing!=null)
            return sharing;

        Trade trade=tradeDao.findByTradeNoLock(request.getTradeNo());

        long amount=0;
        for (TradeProfitSharingReceiverDTO receiver:request.getReceivers()){
            amount+=receiver.getAmount();
        }

        if(trade.getFreezeAmount()<amount){
            throw new BusinessException("可用的分账金额不足");
        }

        trade.setFreezeAmount(trade.getFreezeAmount()-amount);

        sharing=new TradeProfitSharing();
        sharing.setCreateTime(new Date());
        sharing.setUpdateTime(new Date());
        sharing.setStatus(1);
        BeanUtils.copyProperties(request,sharing);

        tradeProfitSharingDao.save(sharing);

        tradeDao.save(trade);

        for (TradeProfitSharingReceiverDTO receiver:request.getReceivers()){
            saveTradeProfitSharingReceiver(sharing,receiver);
        }

        return sharing;
    }


    private TradeProfitSharingReceiver saveTradeProfitSharingReceiver(TradeProfitSharing sharing,TradeProfitSharingReceiverDTO request){
        TradeProfitSharingReceiver receiver=new TradeProfitSharingReceiver();
        BeanUtils.copyProperties(receiver,receiver);
        receiver.setSharingId(sharing.getId());
        receiver.setTradeNo(sharing.getTradeNo());
        tradeProfitSharingReceiverDao.save(receiver);
        return receiver;
    }



}
