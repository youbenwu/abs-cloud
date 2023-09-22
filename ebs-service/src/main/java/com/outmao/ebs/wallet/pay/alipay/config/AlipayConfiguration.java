package com.outmao.ebs.wallet.pay.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝客户端.
 * 
 * @author youbenwu
 */

@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfiguration {

	@Autowired
	private AlipayProperties properties;

	/**
	 * alipay-sdk-java
	 * @return
	 */
	@Bean
	public AlipayClient alipayClient(){
		return new DefaultAlipayClient(properties.getGatewayUrl(),
				properties.getAppid(),
				properties.getAppPrivateKey(),
				properties.getFormate(),
				properties.getCharset(),
				properties.getAlipayPublicKey(),
				properties.getSignType());
	}
	
}
