package com.outmao.ebs.wallet.pay.wechatpay;


import com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;

public interface WechatPayService {


    public PrepayWithRequestPaymentResponse prepayApp(String outTradeNo, long amount, String description);


    public Refund refund(String outTradeNo, String outRefundNo, long amount, String reason);


    public Transaction queryOrder(String outTradeNo);

    public void closeOrder(String outTradeNo);



}
