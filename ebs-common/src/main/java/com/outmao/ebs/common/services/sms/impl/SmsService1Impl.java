package com.outmao.ebs.common.services.sms.impl;



import com.outmao.ebs.common.services.sms.SmsService;
import com.outmao.ebs.common.services.sms.sender.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SmsService1Impl implements SmsService {


    @Autowired
    private SmsSender smsSender;

    @Override
    public void send(String phone, String template, String params) {
        smsSender.send(phone,template,params);
    }

    @Async
    @Override
    public void sendAsync(String phone, String template, String params) {
        send(phone,template,params);
    }
}
