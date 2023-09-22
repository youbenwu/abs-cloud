package com.outmao.ebs.security.validate.sms;



import com.outmao.ebs.common.services.sms.SmsService;
import com.outmao.ebs.common.services.sms.configuration.SmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsCodeSender {

    @Autowired
    SmsService smsService;

	public void send(String phone, String code) {
        smsService.send(phone, SmsConfiguration.SMS_Template_Validate,"{code:\""+code+"\"}");
	}


}
