package com.outmao.ebs.wallet.pay.wechatpay.impl;

import com.outmao.ebs.wallet.pay.wechatpay.WechatPayService;
import com.outmao.ebs.wallet.pay.wechatpay.config.WechatPayConfiguration;
import com.outmao.ebs.wallet.pay.wechatpay.config.WechatPayProperties;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.app.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.AmountReq;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class WechatPayServiceImpl implements WechatPayService {


    @Autowired
    private WechatPayProperties properties;

    @Autowired
    private WechatPayConfiguration configuration;



    @Override
    public PrepayWithRequestPaymentResponse prepayApp(String outTradeNo, long amount, String description) {
        PrepayRequest request = new PrepayRequest();
        Amount a = new Amount();
        a.setTotal((int)amount);
        request.setAmount(a);
        request.setAppid(properties.getAppId());
        request.setMchid(properties.getMchId());
        request.setDescription(description);
        request.setNotifyUrl(properties.getNotifyUrl());
        request.setOutTradeNo(outTradeNo);
        // response包含了调起支付所需的所有参数，可直接用于前端调起支付
        PrepayWithRequestPaymentResponse response = configuration.appServiceExtension().prepayWithRequestPayment(request);
        return response;
    }


    @Override
    public Refund refund(String outTradeNo,String outRefundNo,long amount,String reason) {

        CreateRequest request=new CreateRequest();
        AmountReq a = new AmountReq();
        a.setTotal(amount);
        request.setAmount(a);
        request.setSubMchid(properties.getMchId());
        request.setOutTradeNo(outTradeNo);
        request.setOutRefundNo(outRefundNo);
        request.setNotifyUrl(properties.getNotifyUrl());
        request.setReason(reason);


        try {
            Refund response = configuration.refundService().create(request);
            return response;

        } catch (HttpException e) { // 发送HTTP请求失败
            log.error("发送HTTP请求失败",e);
            // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            log.error("服务返回失败",e);
            // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            log.error("服务返回成功，返回体类型不合法，或者解析返回体失败",e);
            // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
        }
        return null;
    }

    @Override
    public Transaction queryOrder(String outTradeNo) {
        QueryOrderByOutTradeNoRequest request=new QueryOrderByOutTradeNoRequest();
        request.setMchid(properties.getMchId());
        request.setOutTradeNo(outTradeNo);
        Transaction transaction= configuration.appServiceExtension().queryOrderByOutTradeNo(request);
        return transaction;
    }

    @Override
    public void closeOrder(String outTradeNo) {
        CloseOrderRequest request=new CloseOrderRequest();
        request.setMchid(properties.getMchId());
        request.setOutTradeNo(outTradeNo);
        configuration.appServiceExtension().closeOrder(request);
    }


}
