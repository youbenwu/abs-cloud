package com.outmao.ebs.wallet.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.OrderNoUtil;
import com.outmao.ebs.common.util.ServletRequestUtil;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.common.exception.WalletBalanceNotEnoughException;
import com.outmao.ebs.wallet.common.exception.WalletException;
import com.outmao.ebs.wallet.dao.AssetDao;
import com.outmao.ebs.wallet.dao.TransferDao;
import com.outmao.ebs.wallet.domain.TransferDomain;
import com.outmao.ebs.wallet.domain.conver.TransferVOConver;
import com.outmao.ebs.wallet.dto.GetTransferListDTO;
import com.outmao.ebs.wallet.entity.*;
import com.outmao.ebs.wallet.vo.StatsTransferVO;
import com.outmao.ebs.wallet.vo.TransferVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class TransferDomainImpl extends BaseDomain implements TransferDomain {


    @Autowired
    private TransferDao transferDao;

    @Autowired
    private AssetDao assetDao;

    @Autowired
    private TransferVOConver transferVOConver;


    @Transactional
    @Override
    public Transfer transfer(
            Trade trade,
            Wallet from,
            Wallet to,
            Transfer.TransferType fromType,
            Transfer.TransferType toType,
            long amount,
            int businessType,
            String business,
            String remark
    ) {

        Asset assetFrom = from==null?null:assetDao.findByWalletIdAndCurrencyId(from.getId(), trade.getCurrency().getId());
        Asset assetTo =to==null?null: assetDao.findByWalletIdAndCurrencyId(to.getId(), trade.getCurrency().getId());

        if(from!=null) {
            transferAsset(assetFrom,fromType,-amount);
        }

        if(to!=null) {
            transferAsset(assetTo,toType,amount);
        }

        //转帐记录
        Transfer transfer = new Transfer();
        transfer.setTrade(trade);
        transfer.setFrom(from);
        transfer.setTo(to);
        transfer.setCurrency(trade.getCurrency());
        transfer.setAmount(amount);
        transfer.setFromType(fromType);
        transfer.setToType(toType);
        transfer.setRemark(remark);
        transfer.setBusinessType(businessType);
        transfer.setBusiness(business);
        transfer.setCreateTime(new Date());
        transfer.setTransferNo(OrderNoUtil.generateOrderNo());
        transfer.setFromBalance(assetFrom!=null?assetFrom.getBalance():0);
        transfer.setToBalance(assetTo!=null?assetTo.getBalance():0);
        transfer.setActionKey((String) ServletRequestUtil.getAttribute(WalletConstant.action_key));
        transferDao.save(transfer);

        return transfer;
    }

    private void transferAsset(Asset asset,Transfer.TransferType type,long amount){
        if (type == Transfer.TransferType.Balance) {
            if (asset.getBalance() < -amount) {
                throw new WalletBalanceNotEnoughException();
            }
            asset.setBalance(asset.getBalance() + amount);
            asset.setAmount(asset.getAmount() + amount);
        } else if (type == Transfer.TransferType.Frozen) {
            if (asset.getFrozen() < -amount) {
                throw new WalletBalanceNotEnoughException();
            }
            asset.setFrozen(asset.getFrozen() + amount);
            asset.setAmount(asset.getAmount() + amount);
        } else if (type == Transfer.TransferType.Advance) {
            if (asset.getAdvance() < -amount) {
                throw new WalletBalanceNotEnoughException();
            }
            asset.setAdvance(asset.getAdvance() + amount);
            asset.setAmount(asset.getAmount() + amount);
        }
        assetDao.save(asset);
    }

    @Transactional
    @Override
    public Transfer transferBalance(Trade trade) {
        Wallet from=trade.getPayChannel()== PayChannel.WalletPay.getType()?trade.getFrom():null;
        // 转帐
        return transfer(
                trade,
                from,
                trade.getTo(),
                Transfer.TransferType.Balance,
                Transfer.TransferType.Balance,
                trade.getAmount(),
                trade.getBusinessType(),
                trade.getBusiness(),
                trade.getRemark()
        );

    }


    @Transactional
    @Override
    public Transfer transferAdvance(Trade trade) {
        Wallet from=trade.getPayChannel()== PayChannel.WalletPay.getType()?trade.getFrom():null;
        Wallet to=trade.getTo()!=null?trade.getTo():trade.getFrom();
        // 转帐业务
        return transfer(
                trade,
                from,
                to,
                Transfer.TransferType.Balance,
                Transfer.TransferType.Advance,
                trade.getTotalAmount(),
                trade.getBusinessType(),
                trade.getBusiness(),
                trade.getRemark()
        );
    }

    @Transactional
    @Override
    public Transfer transferTo(Trade trade) {
        //计算可用金额
        long amount=trade.getAmount()-trade.getPayAmount()-trade.getRefundAmount();
        if(amount>0){

            //找到支付记录
            Transfer transferFrom=transferDao.findByTradeIdAndToType(trade.getId(), Transfer.TransferType.Advance);
            if(transferFrom==null){
                throw  new WalletException("找不到支付记录");
            }

            trade.setPayAmount(trade.getPayAmount()+amount);

            return transfer(
                    trade,
                    transferFrom.getTo(),
                    trade.getTo(),
                    Transfer.TransferType.Advance,
                    Transfer.TransferType.Balance,
                    trade.getAmount(),
                    trade.getBusinessType(),
                    trade.getBusiness(),
                    trade.getRemark()
            );

        }
        return null;
    }

    @Transactional
    @Override
    public Transfer transferTo(
            Trade trade,
            Wallet to,
            long amount,
            int businessType,
            String business,
            String remark
    ) {

        long maxAmount= trade.getAmount()-trade.getPayAmount()-trade.getRefundAmount();

        if(amount>maxAmount){
            throw new WalletBalanceNotEnoughException();
        }

        //找到支付记录
        Transfer transferFrom=transferDao.findByTradeIdAndToType(trade.getId(), Transfer.TransferType.Advance);
        if(transferFrom==null){
            throw  new WalletException("找不到支付记录");
        }

        trade.setPayAmount(trade.getPayAmount()+amount);

        return transfer(
                trade,
                transferFrom.getTo(),
                to,
                Transfer.TransferType.Advance,
                Transfer.TransferType.Balance,
                amount,
                businessType>0?businessType:trade.getBusinessType(),
                business!=null?business:trade.getBusiness(),
                remark!=null?remark:trade.getRemark()
        );
    }

    @Transactional
    @Override
    public Transfer transferFee(Trade trade) {
        if(trade.getFee()==0)
            return null;
        if (trade.getType() == TradeType.Transfer.getType()) {
            Wallet from=trade.getPayChannel()== PayChannel.WalletPay.getType()?trade.getFrom():null;
            return transfer(
                    trade,
                    from,
                    null,
                    Transfer.TransferType.Balance,
                    Transfer.TransferType.Balance,
                    trade.getFee(),
                    WalletConstant.business_type_fee,
                    "收取手续费",
                    "手续费"
            );
        }else{
            //找到支付记录
            Transfer transferFrom=transferDao.findByTradeIdAndToType(trade.getId(), Transfer.TransferType.Advance);
            if(transferFrom==null){
                throw  new WalletException("找不到支付记录");
            }
            return transfer(
                    trade,
                    transferFrom.getTo(),
                    null,
                    Transfer.TransferType.Advance,
                    Transfer.TransferType.Balance,
                    trade.getFee(),
                    WalletConstant.business_type_fee,
                    "收取手续费",
                    "手续费"
            );
        }
    }

    @Transactional
    @Override
    public Transfer transferRefund(
            Trade trade, //所属交易
            long amount,
            int businessType,
            String business,
            String remark
    ) {

        //找到支付记录
        Transfer transferFrom=transferDao.findByTradeIdAndToType(trade.getId(), Transfer.TransferType.Advance);
        if(transferFrom==null){
            throw  new WalletException("找不到支付记录");
        }

        Wallet from= transferFrom.getTo();
        Wallet to= transferFrom.getFrom();

        if(to==null){
            if(!trade.isRefundOut()) {
                //退回到钱包
                to = trade.getFrom();
            }
        }

        //修改退款金额
        trade.setRefundAmount(trade.getRefundAmount()+amount);

        return this.transfer(
                trade,
                from,
                to,
                transferFrom.getToType(),
                transferFrom.getFromType(),
                amount,
                businessType,
                business,
                remark
        );

    }


    @Override
    public Page<TransferVO> getTransferVOPage(GetTransferListDTO request, Pageable pageable) {
        QTransfer e=QTransfer.transfer;

        Predicate p=getPredicate(request);

        Page<TransferVO> page=queryPage(e,p,transferVOConver,pageable);

        return page;
    }

    private Predicate getPredicate(GetTransferListDTO request){
        QTransfer e=QTransfer.transfer;

        Predicate p=(e.to.id.eq(request.getWalletId()).and(e.toType.eq(Transfer.TransferType.Balance)))
                .or(e.from.id.eq(request.getWalletId()).and(e.fromType.eq(Transfer.TransferType.Balance)));

        p=e.currency.id.eq(request.getCurrencyId()).and(p);

        if(request.getFromTime()!=null&&request.getToTime()!=null){
            System.out.println(request.getFromTime().toLocaleString()+" " +request.getToTime().toLocaleString());
            p=e.createTime.between(request.getFromTime(),request.getToTime()).and(p);
        }
        return p;
    }

    @Override
    public StatsTransferVO getStatsTransferVO(GetTransferListDTO request) {
        QTransfer e=QTransfer.transfer;

        Predicate p=e.currency.id.eq(request.getCurrencyId());

        if(request.getFromTime()!=null&&request.getToTime()!=null){
            p=e.createTime.between(request.getFromTime(),request.getToTime()).and(p);
        }

        Long fromAmount=QF.select(e.amount.sum()).from(e).where(e.from.id.eq(request.getWalletId()).and(e.fromType.eq(Transfer.TransferType.Balance)).and(p)).fetchOne();

        Long toAmount=QF.select(e.amount.sum()).from(e).where(e.to.id.eq(request.getWalletId()).and(e.toType.eq(Transfer.TransferType.Balance)).and(p)).fetchOne();

        StatsTransferVO vo=new StatsTransferVO();

        vo.setFromAmount(fromAmount==null?0:fromAmount);
        vo.setToAmount(toAmount==null?0:toAmount);
        vo.setAmount(vo.getToAmount()-vo.getFromAmount());

        return vo;
    }


}
