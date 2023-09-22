package com.outmao.ebs.common.services.oss.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "oss.properties")
public class OSSProperties {


	
	private String endpoint;
	
	private String accessKeyId;
	
	private String accessKeySecret;
	
	private String bucketPublicRead;
	
	private String bucketPublicReadUrl;

	private String ramAccessKeyId;

	private String ramAccessKeySecret;

	private String roleArn;

	public String getUploadUrl(){
		String url="http://"+bucketPublicRead+"."+endpoint.replace("http://","");
		return url;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketPublicRead() {
		return bucketPublicRead;
	}

	public void setBucketPublicRead(String bucketPublicRead) {
		this.bucketPublicRead = bucketPublicRead;
	}

	public String getBucketPublicReadUrl() {
		return bucketPublicReadUrl;
	}

	public void setBucketPublicReadUrl(String bucketPublicReadUrl) {
		this.bucketPublicReadUrl = bucketPublicReadUrl;
	}


	public String getRamAccessKeyId() {
		return ramAccessKeyId;
	}

	public void setRamAccessKeyId(String ramAccessKeyId) {
		this.ramAccessKeyId = ramAccessKeyId;
	}

	public String getRamAccessKeySecret() {
		return ramAccessKeySecret;
	}

	public void setRamAccessKeySecret(String ramAccessKeySecret) {
		this.ramAccessKeySecret = ramAccessKeySecret;
	}
}
