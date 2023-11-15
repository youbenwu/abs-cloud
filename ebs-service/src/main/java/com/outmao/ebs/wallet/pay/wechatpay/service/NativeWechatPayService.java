package com.outmao.ebs.wallet.pay.wechatpay.service;


import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;

public interface NativeWechatPayService {

    public PrepayResponse prepay(String outTradeNo, long amount, String description);


}
