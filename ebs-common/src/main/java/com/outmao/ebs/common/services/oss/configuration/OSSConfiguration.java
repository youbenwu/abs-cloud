package com.outmao.ebs.common.services.oss.configuration;


import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({ OSSProperties.class })
@Configuration
public class OSSConfiguration {


    @Autowired
    public OSSProperties properties;


    @Bean
    public OSSClient ossClient(){
        return new OSSClient(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
    }


}
