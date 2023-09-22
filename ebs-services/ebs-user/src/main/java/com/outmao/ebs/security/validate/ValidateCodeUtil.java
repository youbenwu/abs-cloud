package com.outmao.ebs.security.validate;



import com.outmao.ebs.common.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ValidateCodeUtil {

	private static HashMap<String,ValidateCode> codes=new HashMap<>();

	public static void saveCode(String phone,ValidateCode code){
		codes.put(phone,code);
	}

	public static ValidateCode getCode(String phone){
        return codes.get(phone);
	}
	
	public static void verify(String phone,String validateCode, HttpServletRequest request) {
		System.out.println("session:"+request.getSession().getId());
		//ValidateCode vcode = (ValidateCode) request.getSession().getAttribute(SecurityConstants.SESSION_KEY_VERIFY_CODE);
		ValidateCode vcode = getCode(phone);
		if (vcode == null || !vcode.getCode().equals(validateCode)) {
			throw new BusinessException("验证码错误");
		}
		if (vcode.isExpried()) {
			codes.remove(phone);
			throw new BusinessException("验证码超时");
		}
		//request.getSession().removeAttribute(SecurityConstants.SESSION_KEY_VERIFY_CODE);
	}

}
