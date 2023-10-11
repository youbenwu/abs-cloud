package com.outmao.ebs.common.services.sms.configuration;



import com.outmao.ebs.common.services.sms.sender.AliSmsSender;
import com.outmao.ebs.common.services.sms.sender.JuheSmsSender;
import com.outmao.ebs.common.services.sms.sender.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfiguration {

	public static String SMS_Platform_ALI = "ali";
	public static String SMS_Platform_JUHE = "juhe";

	public static String SMS_Template_Validate = "validate";

	@Autowired
	private SmsProperties smsProperties;

	@Bean
	public SmsSender smsSender() {
		if(SMS_Platform_ALI.equals(smsProperties.getActive())){
			return new AliSmsSender();
		}else if(SMS_Platform_JUHE.equals(smsProperties.getActive())){
			return new JuheSmsSender();
		}
		return new AliSmsSender();
	}



}
