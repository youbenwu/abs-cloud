package com.outmao.ebs.security.validate.sms;


import com.outmao.ebs.security.validate.ValidateCode;
import com.outmao.ebs.security.validate.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			code += rand;
		}
		return new ValidateCode(code, 60);
	}
	

}
