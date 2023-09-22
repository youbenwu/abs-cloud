package com.outmao.ebs.common.services.jisu.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *
 *
 * @author youbenwu
 */


@Data
@Configuration
@EnableConfigurationProperties(JisuProperties.class)
@ConfigurationProperties(prefix = "jisu")
public class JisuProperties {

	private String api;

	private String appkey;


}
