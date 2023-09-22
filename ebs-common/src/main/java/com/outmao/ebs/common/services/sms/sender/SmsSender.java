package com.outmao.ebs.common.services.sms.sender;


public interface SmsSender {

    /*
     * 发送短信
     * phone--手机号
     * template--短信模板编号
     * params--短信参数JSON格式
     *
     * */
    void send(String phone, String template, String params);
    
}
