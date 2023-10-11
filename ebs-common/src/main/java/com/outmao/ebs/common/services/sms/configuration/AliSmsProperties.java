package com.outmao.ebs.common.services.sms.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * alipay sms的参数配置
 *
 * @author youbenwu
 */


@Data
@Configuration
@EnableConfigurationProperties(AliSmsProperties.class)
@ConfigurationProperties(prefix = "sms.ali")
public class AliSmsProperties {

	/** 产品名称:云通信短信API产品,开发者无需替换 */
	private String product="Dysmsapi";

	/** 产品域名,开发者无需替换 */
	private String domain="dysmsapi.aliyuncs.com";

	/** 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) */
	private String accessKeyId;

	/** 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) */
	private String accessKeySecret;

	/** 短信签名-可在短信控制台中找到 */
	private String signName;

	//key--templateCode 自定义编号
	//value-template 第三方模板
	private Map<String,String> templates;


}
