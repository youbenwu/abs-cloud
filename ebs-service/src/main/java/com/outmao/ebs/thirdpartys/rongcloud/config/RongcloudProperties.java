package com.outmao.ebs.thirdpartys.rongcloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



@Data
@ConfigurationProperties(prefix = "rongcloud")
public class RongcloudProperties {


	private String appKey;

	private String appSecret;



}
