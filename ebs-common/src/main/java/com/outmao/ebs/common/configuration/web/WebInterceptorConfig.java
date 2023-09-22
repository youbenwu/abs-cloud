package com.outmao.ebs.common.configuration.web;


import com.outmao.ebs.common.configuration.log.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 增加拦截器
 * 
 * @author: yeyi
 * @date: 2019年8月29日
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }

}
