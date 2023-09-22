package com.outmao.ebs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;


@ServletComponentScan
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class EbsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbsApplication.class, args);
	}
}


