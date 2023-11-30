package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.wallet.common.constant.*;
import com.outmao.ebs.wallet.domain.CashDomain;
import com.outmao.ebs.wallet.dto.*;
import com.outmao.ebs.wallet.entity.Cash;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.dto.PayWalletDTO;
import com.outmao.ebs.wallet.pay.service.PayService;
import com.outmao.ebs.wallet.service.CashService;
import com.outmao.ebs.wallet.service.WalletService;
import com.outmao.ebs.wallet.vo.CashVO;
import com.outmao.ebs.wallet.vo.StatsCashStatusVO;
import com.outmao.ebs.wallet.vo.StatsCashVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

        //创建交易
        TradeVO trade=cashPayPrepare(cash);

        Currency currency=walletService.getCurrencyById("RMB");
        cash.setTotalAmount(currency.getAmountValue(trade.getTotalAmount()));
        cash.setAmount(currency.getAmountValue(trade.getAmount()));
        cash.setFee(currency.getAmountValue(trade.getFee()));

        //去支付
        PayWalletDTO payWalletDTO=new PayWalletDTO();
        payWalletDTO.setTradeNo(trade.getTradeNo());
        payService.payWallet(payWalletDTO);

        return cash;
    }

    @Override
    public void deleteCashById(Long id) {
        cashDomain.deleteCashById(id);
    }

    private TradeVO cashPayPrepare(Cash cash) {

        Currency currency=walletService.getCurrencyById("RMB");

        long amount=(long) (cash.getAmount()*currency.getPar());
        long fee=currency.getCashFee(amount);
        amount=amount-fee;

        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(cash.getOrderNo());
        dto.setFromId(cash.getWallet().getId());
        dto.setCurrencyId(currency.getId());
        dto.setAmount(amount);
        dto.setFee(fee);
        dto.setRemark(cash.getRemark());
        dto.setType(TradeType.Cash.getType());
        dto.setBusinessType(WalletConstant.business_type_cash);
        dto.setBusiness("用户提现");
        dto.setPayChannel(PayChannel.WalletPay.getType());

        return payService.tradePrepare(dto);
    }

    @Transactional
    @Override
    public Cash setCashStatus(SetCashStatusDTO request) {
        Cash cash= cashDomain.setCashStatus(request);
        if(cash.getStatus()== CashStatus.FINISHED.getStatus()){
            if(cash.getOutStatus()== CashOutStatus.UNKNOWN.getStatus()){
                payService.fundTransToaccountTransfer(cash.getOrderNo(),cash.getAmount(),cash.getAlipayAccount().getAccount(),cash.getAlipayAccount().getName(),cash.getRemark());
                cash.setOutStatus(CashOutStatus.SUCCEED.getStatus());
                cash.setOutStatusRemark(CashOutStatus.SUCCEED.getStatusRemark());
//                try {
//                    payService.fundTransToaccountTransfer(cash.getOrderNo(),cash.getAmount(),cash.getAlipayAccount().getAccount(),cash.getAlipayAccount().getName(),cash.getRemark());
//                    cash.setOutStatus(CashOutStatus.SUCCEED.getStatus());
//                    cash.setOutStatusRemark(CashOutStatus.SUCCEED.getStatusRemark());
//                }catch (Exception e){
//                    cash.setOutStatus(CashOutStatus.FAIL.getStatus());
//                    cash.setOutStatusRemark(e.getMessage());
//                }
            }
            payService.tradeFinish(cash.getOrderNo());
        }else if(cash.getStatus()== CashStatus.CLOSED.getStatus()){
            payService.tradeClose(cash.getOrderNo());
        }
        return cash;
    }

    @Override
    public Page<CashVO> getCashVOPage(GetCashListDTO request, Pageable pageable) {
        return cashDomain.getCashVOPage(request,pageable);
    }

    @Override
    public void statusChanged(Trade trade) {
        if(trade.getBusinessType()== WalletConstant.business_type_cash) {
            if(trade.getStatus()==TradeStatus.TRADE_SUCCEED.getStatus()) {
                setCashStatus(new SetCashStatusDTO(trade.getTradeNo(), trade.getStatus(), trade.getStatusRemark()));
            }
        }
    }

    @Override
    public StatsCashVO getStatsCashVO(GetStatsCashDTO request) {
        return cashDomain.getStatsCashVO(request);
    }


    @Override
    public List<StatsCashStatusVO> getStatsCashStatusVOList() {
        return cashDomain.getStatsCashStatusVOList();
    }
}
