package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.domain.CashDomain;
import com.outmao.ebs.wallet.dto.CashDTO;
import com.outmao.ebs.wallet.dto.GetCashListDTO;
import com.outmao.ebs.wallet.dto.SetCashStatusDTO;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.CashService;
import com.outmao.ebs.wallet.vo.CashVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CashServiceImpl implements CashService, CommandLineRunner {


    @Autowired
    private CashDomain cashDomain;


    @Autowired
    private PayService payService;



    @Override
    public void run(String... args) throws Exception {
       payService.addStatusListener(this);
    }

    @Transactional
    @Override
    public Cash saveCash(CashDTO request) {

        Cash cash= cashDomain.saveCash(request);

        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(cash.getCashNo());
        dto.setFromId(cash.getWallet().getId());
        dto.setCurrencyId("RMB");
        dto.setAmount(cash.getAmount());
        dto.setRemark(cash.getRemark());
        dto.setType(TradeType.Cash.getType());
        dto.setBusinessType(WalletConstant.business_type_cash);
        dto.setBusiness("用户提现");
        dto.setPayChannel(PayChannel.WalletPay.getType());

        payService.tradePrepare(dto);

        return cash;
    }

    @Override
    public Cash setCashStatus(SetCashStatusDTO request) {
        return cashDomain.setCashStatus(request);
    }

    @Override
    public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable) {
        return cashDomain.getCashVOPage(request,pageable);
    }

    @Override
    public void statusChanged(Trade trade) {
        if(trade.getBusinessType()== WalletConstant.business_type_cash) {
            setCashStatus(new SetCashStatusDTO(trade.getTradeNo(), trade.getStatus(), trade.getStatusRemark()));
        }
    }


}
