package com.outmao.ebs.wallet.pay.wechatpay.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 微信支付的参数配置
 *
 * @author mengday zhang
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "pay.wechatpay")
public class WechatPayProperties  {

	/** 商户号 */
	public String merchantId;
	/** 商户API私钥路径 */
	public String privateKeyPath;
	/** 商户证书序列号 */
	public String merchantSerialNumber;
	/** 商户APIV3密钥 */
	public String apiV3Key;


	/** 退款异步通知地址 */
	private String notifyUrl;


	/** 公众账号ID */
	private String appId;

	/** 商户号 */
	private String mchId;



}
