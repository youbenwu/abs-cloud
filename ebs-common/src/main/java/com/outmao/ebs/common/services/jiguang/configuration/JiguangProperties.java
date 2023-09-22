package com.outmao.ebs.common.services.jiguang.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 *
 *
 * @author youbenwu
 *
 *
 */


@Data
@Configuration
@EnableConfigurationProperties(JiguangProperties.class)
@ConfigurationProperties(prefix = "jiguang")
public class JiguangProperties {

	private String mastersecret;

	private String appkey;

	private boolean production=true;


}
