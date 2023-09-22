package com.outmao.ebs.common.services.sms;

public interface SmsService {

    /*
     * 发送短信
     * phone--手机号
     * template--短信模板名称
     * params--短信Map参数JSON格式
     *
     * */
    void send(String phone, String template, String params);

    void sendAsync(String phone, String template, String params);

}
