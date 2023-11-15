package com.outmao.ebs.wallet.pay.wechatpay.service;


import com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse;

public interface AppWechatPayService {

    public PrepayWithRequestPaymentResponse prepay(String outTradeNo, long amount, String description);


}
