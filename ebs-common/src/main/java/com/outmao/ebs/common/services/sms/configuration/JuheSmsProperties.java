package com.outmao.ebs.common.services.sms.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Data
@Configuration
@EnableConfigurationProperties(JuheSmsProperties.class)
@ConfigurationProperties(prefix = "sms.juhe")
public class JuheSmsProperties {

	//key--templateCode 自定义编号
	//value-template 第三方模板
	private Map<String,String> templates;


}
