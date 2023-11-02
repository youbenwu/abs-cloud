package com.outmao.ebs.thirdpartys.rongcloud.config;


import io.rong.RongCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableConfigurationProperties(RongcloudProperties.class)
public class RongcloudConfiguration {

	@Autowired
	private RongcloudProperties properties;


	@Bean
	public RongCloud rongCloud(){
		RongCloud rongCloud = RongCloud.getInstance(properties.getAppKey(), properties.getAppSecret());
		return rongCloud;
	}
	
}
