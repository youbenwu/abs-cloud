package com.outmao.ebs.common.configuration.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "config")
@Configuration
@EnableConfigurationProperties({ Config.class})
public class Config {

	/*是否生产环境*/
	private boolean production;

	/*域名地址 如 outmao.com */
	private String serverName;

	/*静态文件存放位置*/
	private String filePath;

	/*访问服务的基URL*/
	private String baseUrl;

	/*上传文件到OSS*/
	private boolean useOss;


}
