package com.outmao.ebs.wallet.pay.wechatpay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * 微信支付的参数配置
 *
 */
@Data
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

	/** 小程序、公众号 ID */
	private String appId;


}
