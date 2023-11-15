package com.outmao.ebs.wallet.pay.wechatpay.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.wallet.pay.wechatpay.config.WechatPayConfiguration;
import com.outmao.ebs.wallet.pay.wechatpay.config.WechatPayProperties;
import com.outmao.ebs.wallet.pay.wechatpay.service.JsapiWechatPayService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JsapiWechatPayServiceImpl implements JsapiWechatPayService {

    @Autowired
    private WechatPayProperties properties;

    @Autowired
    private WechatPayConfiguration configuration;

    @Override
    public PrepayWithRequestPaymentResponse prepay(String outTradeNo, long amount, String description,String openId) {
        try{
            PrepayRequest request=getPrepayRequest(outTradeNo,amount,description,openId);
            // response包含了调起支付所需的所有参数，可直接用于前端调起支付
            PrepayWithRequestPaymentResponse response = configuration.jsapiServiceExtension().prepayWithRequestPayment(request);
            return response;
        }catch (Exception e){
            log.error("微信获取调起支付参数错误",e);
            throw new BusinessException("微信支付出错");
        }
    }

    private PrepayRequest getPrepayRequest(String outTradeNo, long amount, String description,String openId){
        PrepayRequest request = new PrepayRequest();
        request.setAppid(properties.getAppId());
        request.setMchid(properties.getMerchantId());
        request.setNotifyUrl(properties.getNotifyUrl());
        Payer payer=new Payer();
        payer.setOpenid(openId);
        request.setPayer(payer);
        request.setOutTradeNo(outTradeNo);
        request.setDescription(description);
        Amount a = new Amount();
        a.setTotal((int)amount);
        request.setAmount(a);
        return request;
    }


}
