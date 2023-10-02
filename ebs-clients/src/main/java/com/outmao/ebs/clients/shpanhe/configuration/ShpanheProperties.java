package com.outmao.ebs.clients.shpanhe.configuration;

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
@EnableConfigurationProperties(ShpanheProperties.class)
@ConfigurationProperties(prefix = "shpanhe")
public class ShpanheProperties {

	private String ticketApi;

	private String appKey;


}
