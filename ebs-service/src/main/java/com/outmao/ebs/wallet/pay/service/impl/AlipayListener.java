package com.outmao.ebs.wallet.pay.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.alipay.AlipayNotifyListener;
import com.outmao.ebs.wallet.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AlipayListener implements AlipayNotifyListener {

    @Autowired
    private TradeService tradeService;

    @Override
    public void notify(String tradeStatus, String out_trade_no, String trade_no, String total_amount) {

        Trade trade = tradeService.getTradeByTradeNo(out_trade_no);

        String amount = String.format("%.2f", trade.getTotalAmount()/100.0);

        if (!amount.equals(total_amount)) {
            throw new BusinessException("金额不对");
        }

        //同步交易状态
        if (tradeStatus.equals("TRADE_FINISHED")) {
            // 交易结束，不可退款
            tradeService.tradePay(trade.getTradeNo());
        } else if (tradeStatus.equals("TRADE_SUCCESS")) {
            // 交易支付成功
            tradeService.tradePay(trade.getTradeNo());
        } else if (tradeStatus.equals("TRADE_CLOSED")) {
            // 未付款交易超时关闭，或支付完成后全额退款
            tradeService.tradeClose(trade.getTradeNo());
        }
    }



}
