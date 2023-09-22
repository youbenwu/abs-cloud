package com.outmao.ebs.wallet.pay.wxpay.config;

import com.outmao.ebs.wallet.pay.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 *
 * @author mengday zhang
 */
@Configuration
@EnableConfigurationProperties(MyWXPayConfig.class)
public class WXPayConfiguration {

	@Autowired
	private MyWXPayConfig wxPayConfig;

	/**
	 * useSandbox 沙盒环境
	 * @return
	 * @throws Exception 
	 */
	@Bean
	public WXPay wxPay() throws Exception {
		return new WXPay(wxPayConfig,wxPayConfig.getNotifyUrl(), true, wxPayConfig.getUseSandbox() );
	}

	@Bean
	public WXPayClient wxPayClient() throws Exception {
		return new WXPayClient(wxPayConfig,wxPayConfig.getNotifyUrl(), wxPayConfig.getUseSandbox());
	}
}
