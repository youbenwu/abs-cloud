package com.outmao.ebs.wallet.pay.wechatpay.config;


import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.app.AppServiceExtension;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.refund.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 *
 * @author mengday zhang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WechatPayProperties.class)
public class WechatPayConfiguration {

	@Autowired
	private WechatPayProperties properties;

	@Bean
	public Config payConfig(){
		log.info("/*初始化微信支付配置*/");
		return new RSAAutoCertificateConfig.Builder()
				.merchantId(properties.getMerchantId())
				.privateKeyFromPath(properties.getPrivateKeyPath())
				.merchantSerialNumber(properties.getMerchantSerialNumber())
				.apiV3Key(properties.getApiV3Key())
				.build();
	}


	@Bean
	public NativePayService nativePayService(){
		log.info("/*构建NativePayService*/");
		NativePayService service = new NativePayService.Builder().config(payConfig()).build();
		return service;
	}


	@Bean
	public AppServiceExtension appServiceExtension(){
		log.info("/*构建AppServiceExtension*/");
		AppServiceExtension service=new AppServiceExtension.Builder().config(payConfig()).build();
		return service;
	}

	@Bean
	public JsapiServiceExtension jsapiServiceExtension(){
		log.info("/*构建JsapiServiceExtension*/");
		JsapiServiceExtension service=new JsapiServiceExtension.Builder().config(payConfig()).build();
		return service;
	}

	@Bean
	public H5Service h5Service(){
		log.info("/*构建H5Service*/");
		H5Service service=new H5Service.Builder().config(payConfig()).build();
		return service;
	}


	@Bean
	public RefundService refundService(){
		log.info("/*构建RefundServicen*/");
		RefundService service=new RefundService.Builder().config(payConfig()).build();
		return service;
	}



}
