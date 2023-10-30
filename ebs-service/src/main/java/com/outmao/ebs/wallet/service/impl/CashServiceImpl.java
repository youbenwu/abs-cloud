package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.domain.CashDomain;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.CashService;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.TradeVO;
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
    private WalletService walletService;

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

        return cash;
    }

    @Override
    public TradeVO cashPayPrepare(CashPayPrepare request) {
        Cash cash = cashDomain.getCashByOrderNo(request.getOrderNo());

        Currency currency=walletService.getCurrencyById("RMB");
        long amount=(long) (cash.getAmount()*currency.getPar());

        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(request.getOrderNo());
        dto.setFromId(cash.getWallet().getId());
        dto.setCurrencyId(currency.getId());
        dto.setAmount(amount);
        dto.setRemark(cash.getRemark());
        dto.setType(TradeType.Cash.getType());
        dto.setBusinessType(WalletConstant.business_type_cash);
        dto.setBusiness("用户提现");
        dto.setPayChannel(PayChannel.WalletPay.getType());

        return payService.tradePrepare(dto);
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
