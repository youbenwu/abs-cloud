package com.outmao.ebs.wallet.pay.wechatpay.config;


import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.app.AppServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.refund.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 *
 * @author mengday zhang
 */
@Configuration
@EnableConfigurationProperties(WechatPayProperties.class)
public class WechatPayConfiguration {

	@Autowired
	private WechatPayProperties properties;


	private Config payConfig;


	public Config payConfig(){
		if(payConfig==null)
		payConfig = new RSAAutoCertificateConfig.Builder()
						.merchantId(properties.getMerchantId())
						.privateKeyFromPath(properties.getPrivateKeyPath())
						.merchantSerialNumber(properties.getMerchantSerialNumber())
						.apiV3Key(properties.getApiV3Key())
						.build();
		return payConfig;
	}



	public NativePayService nativePayService(){
		NativePayService service = new NativePayService.Builder().config(payConfig()).build();
		return service;
	}



	public AppServiceExtension appServiceExtension(){
		AppServiceExtension service=new AppServiceExtension.Builder().config(payConfig()).build();
		return service;
	}


	public JsapiServiceExtension jsapiServiceExtension(){
		JsapiServiceExtension service=new JsapiServiceExtension.Builder().config(payConfig()).build();
		return service;
	}



	public RefundService refundService(){
		RefundService service=new RefundService.Builder().config(payConfig()).build();
		return service;
	}


}
