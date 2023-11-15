package com.outmao.ebs.wallet.pay.wechatpay.service;


import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;

public interface JsapiWechatPayService {

    public PrepayWithRequestPaymentResponse prepay(String outTradeNo, long amount, String description,String openId);


}
