package com.outmao.ebs.common.services.sms.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * alipay sms的参数配置
 *
 * @author youbenwu
 */


@Data
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

	private String active="alipay";

}
