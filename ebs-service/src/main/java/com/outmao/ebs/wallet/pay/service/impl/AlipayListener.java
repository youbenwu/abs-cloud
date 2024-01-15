package com.outmao.ebs.wallet.pay.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.dto.TradePayDTO;
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

        //if (trade_status.equals("TRADE_FINISHED")) {
        // 判断该笔订单是否在商户网站中已经做过处理
        // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
        // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
        // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
        // 如果有做过处理，不执行商户的业务程序

        // 注意：
        // 如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
        // 如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

        //} else if (trade_status.equals("TRADE_SUCCESS")) {
        // 判断该笔订单是否在商户网站中已经做过处理
        // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
        // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
        // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
        // 如果有做过处理，不执行商户的业务程序

        // 注意：
        // 如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

        //}

        Trade trade = tradeService.getTradeByTradeNo(out_trade_no);

        String amount = String.format("%.2f", trade.getTotalAmount()/100.0);

        if (!amount.equals(total_amount)) {
            throw new BusinessException("金额不对");
        }

        //同步交易状态
        if (tradeStatus.equals("TRADE_FINISHED")) {
            // 交易结束，不可退款
            tradeService.tradeFinish(trade.getTradeNo());
        } else if (tradeStatus.equals("TRADE_SUCCESS")) {
            // 交易支付成功
            TradePayDTO payDTO=new TradePayDTO();
            payDTO.setTradeNo(trade.getTradeNo());
            payDTO.setPayChannel(PayChannel.AliPay.getType());
            payDTO.setOutPayType(trade.getOutPayType());
            payDTO.setReceiptAmount(trade.getTotalAmount());
            tradeService.tradePay(payDTO);
        } else if (tradeStatus.equals("TRADE_CLOSED")) {
            // 未付款交易超时关闭，或支付完成后全额退款
            tradeService.tradeClose(trade.getTradeNo());
        }

    }



}
