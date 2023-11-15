package com.outmao.ebs.wallet.pay.wechatpay.service;


import com.wechat.pay.java.service.payments.h5.model.PrepayResponse;

public interface H5WechatPayService {

    public PrepayResponse prepay(String outTradeNo, long amount, String description);


}
