package com.outmao.ebs.wallet.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.domain.RechargeDomain;
import com.outmao.ebs.wallet.dto.GetRechargeListDTO;
import com.outmao.ebs.wallet.dto.RechargeDTO;
import com.outmao.ebs.wallet.dto.SetRechargeStatusDTO;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.entity.Recharge;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.RechargeService;
import com.outmao.ebs.wallet.vo.RechargeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RechargeServiceImpl extends BaseService implements RechargeService, CommandLineRunner {

    @Autowired
    private RechargeDomain rechargeDomain;


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

        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(recharge.getRechargeNo());
        dto.setFromId(recharge.getWallet().getId());
        dto.setCurrencyId("RMB");
        dto.setAmount(recharge.getAmount());
        dto.setRemark(recharge.getRemark());
        dto.setType(TradeType.Cash.getType());
        dto.setBusinessType(WalletConstant.business_type_recharge_pay);
        dto.setBusiness("用户充值支付");
        dto.setPayChannel(PayChannel.WalletPay.getType());

        payService.tradePrepare(dto);

        return recharge;
    }

    @Override
    public Recharge setRechargeStatus(SetRechargeStatusDTO request) {
        return rechargeDomain.setRechargeStatus(request);
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
