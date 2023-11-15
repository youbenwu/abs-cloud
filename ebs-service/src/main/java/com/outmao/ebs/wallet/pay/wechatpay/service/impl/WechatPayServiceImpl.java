package com.outmao.ebs.wallet.pay.wechatpay.service.impl;

import com.outmao.ebs.wallet.pay.wechatpay.service.*;
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
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class WechatPayServiceImpl implements WechatPayService {


    @Autowired
    private WechatPayProperties properties;

    @Autowired
    private WechatPayConfiguration configuration;

    @Autowired
    private AppWechatPayService appWechatPayService;

    @Autowired
    private JsapiWechatPayService jsapiWechatPayService;

    @Autowired
    private NativeWechatPayService nativeWechatPayService;

    @Autowired
    private H5WechatPayService h5WechatPayService;

    @Override
    public Object prepayApp(String outTradeNo, long amount, String description) {
        return appWechatPayService.prepay(outTradeNo,amount,description);
    }

    @Override
    public Object prepayJsapi(String outTradeNo, long amount, String description,String openId) {
        return jsapiWechatPayService.prepay(outTradeNo,amount,description,openId);
    }

    @Override
    public Object prepayNative(String outTradeNo, long amount, String description) {
        return nativeWechatPayService.prepay(outTradeNo,amount,description);
    }

    @Override
    public Object prepayH5(String outTradeNo, long amount, String description) {
        return h5WechatPayService.prepay(outTradeNo,amount,description);
    }

    @Override
    public Refund refund(String outTradeNo,String outRefundNo,long amount,String reason) {

        CreateRequest request=new CreateRequest();
        AmountReq a = new AmountReq();
        a.setTotal(amount);
        request.setAmount(a);
        request.setSubMchid(properties.getMerchantId());
        request.setOutTradeNo(outTradeNo);
        request.setOutRefundNo(outRefundNo);
        request.setNotifyUrl(properties.getNotifyUrl());
        request.setReason(reason);


        try {
            Refund response = configuration.refundService().create(request);
            return response;
        } catch (HttpException e) { // 发送HTTP请求失败
            log.error("微信退款失败：发送HTTP请求失败",e);
            // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            log.error("微信退款失败：服务返回失败",e);
            // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            log.error("微信退款失败：服务返回成功，返回体类型不合法，或者解析返回体失败",e);
            // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
        }
        return null;
    }

    @Override
    public Transaction queryOrder(String outTradeNo) {
        try {
            QueryOrderByOutTradeNoRequest request=new QueryOrderByOutTradeNoRequest();
            request.setMchid(properties.getMerchantId());
            request.setOutTradeNo(outTradeNo);
            Transaction transaction= configuration.appServiceExtension().queryOrderByOutTradeNo(request);
            log.info("/*微信支付订单查询*/\n{}",transaction);
            return transaction;
        }catch (ServiceException e){
            if(e.getErrorCode().equals("ORDER_NOT_EXIST")){
                return null;
            }
            throw e;
        }
    }

    @Override
    public void closeOrder(String outTradeNo) {
        CloseOrderRequest request=new CloseOrderRequest();
        request.setMchid(properties.getMerchantId());
        request.setOutTradeNo(outTradeNo);
        configuration.appServiceExtension().closeOrder(request);
    }



}
