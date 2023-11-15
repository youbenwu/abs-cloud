package com.outmao.ebs.wallet.pay.wechatpay.service;


import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;

public interface WechatPayService {


    public Object prepayApp(String outTradeNo, long amount, String description);

    public Object prepayJsapi(String outTradeNo, long amount, String description,String openId);

    public Object prepayNative(String outTradeNo, long amount, String description);

    public Object prepayH5(String outTradeNo, long amount, String description);

    public Refund refund(String outTradeNo, String outRefundNo, long amount, String reason);

    public Transaction queryOrder(String outTradeNo);

    public void closeOrder(String outTradeNo);



}
