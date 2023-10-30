package com.outmao.ebs.wallet.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.domain.RechargeDomain;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.RechargeService;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.RechargeVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class RechargeServiceImpl extends BaseService implements RechargeService, CommandLineRunner {

    @Autowired
    private RechargeDomain rechargeDomain;


    @Autowired
    private WalletService walletService;

    @Autowired
    private TradeService tradeService;


    @Autowired
    private PayService payService;


    //
    @Override
    public void run(String... args) throws Exception {
        payService.addStatusListener(this);
    }


    @Transactional
    @Override
    public Recharge saveRecharge(RechargeDTO request) {

        Recharge recharge= rechargeDomain.saveRecharge(request);

        Currency currency=walletService.getCurrencyById(recharge.getRechargeAmount().getCurrencyId());

        Currency rmb=walletService.getCurrencyById("RMB");

        double amount=recharge.getRechargeAmount().getAmount()*currency.getPar()/rmb.getPar();

        recharge.setAmount(amount);


        return recharge;
    }


    @Override
    public TradeVO rechargePayPrepare(RechargePayPrepare request) {

        Recharge recharge=rechargeDomain.getRechargeByOrderNo(request.getOrderNo());

        Currency rmb=walletService.getCurrencyById("RMB");

        long amount=(long) (recharge.getAmount()*rmb.getOneUnit());

        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(recharge.getRechargeNo());
        dto.setFromId(recharge.getWallet().getId());
        dto.setCurrencyId(rmb.getId());
        dto.setAmount(amount);
        dto.setRemark(recharge.getRemark());
        dto.setType(TradeType.Cash.getType());
        dto.setBusinessType(WalletConstant.business_type_recharge_pay);
        dto.setBusiness("用户充值支付");
        dto.setPayChannel(PayChannel.WalletPay.getType());

        return payService.tradePrepare(dto);
    }

    @Transactional
    @Override
    public Recharge setRechargeStatus(SetRechargeStatusDTO request) {
        Recharge recharge= rechargeDomain.setRechargeStatus(request);
        if(recharge.getStatus()== TradeStatus.TRADE_FINISHED.getStatus()){
            recharge(recharge);
        }
        return recharge;
    }

    private void recharge(Recharge recharge){

        Currency currency=walletService.getCurrencyById(recharge.getRechargeAmount().getCurrencyId());
        long amount=(long)( recharge.getRechargeAmount().getAmount()*currency.getOneUnit());
        tradeService.tradeRecharge(new TradeRechargeDTO(recharge.getWallet().getId(), currency.getId(), amount));

    }

    @Override
    public Page<RechargeVO> getRechargeVOPage(GetRechargeListDTO request, Pageable pageable) {
        return rechargeDomain.getRechargeVOPage(request,pageable);
    }

    @Override
    public void statusChanged(Trade trade) {
        if(trade.getBusinessType()== WalletConstant.business_type_recharge_pay) {
            setRechargeStatus(new SetRechargeStatusDTO(trade.getTradeNo(), trade.getStatus(), trade.getStatusRemark()));
        }
    }

}
