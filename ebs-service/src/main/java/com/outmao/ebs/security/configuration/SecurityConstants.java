package com.outmao.ebs.security.configuration;

public class SecurityConstants {
	
	/**
     * token
     */
    public static String PARAMETER_KEY_TOKEN = "token";
	
	/**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static String PARAMETER_KEY_MOBILE = "phone";
    
	/**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static String PARAMETER_KEY_VERIFY_CODE = "verifyCode";

    /**
     *
     * 微信码KEY
     *
     */
    public static String PARAMETER_KEY_WX_CODE = "code";



}
