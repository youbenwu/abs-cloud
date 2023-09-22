package com.outmao.ebs.common.services.juhe.configuration;

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
@EnableConfigurationProperties(JuheProperties.class)
@ConfigurationProperties(prefix = "juhe")
public class JuheProperties {

	private String api;

	private String key;


}
