package com.outmao.ebs.common.configuration.swagger;

import com.outmao.ebs.common.configuration.sys.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
*
* Swagger配置
*
* */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Autowired
	private Config config;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.host(config.getBaseUrl().replace("http://","").replace("https://",""))
				.apiInfo(apiInfo())
				.select()
				// 自行修改为自己的包路径
				.apis(RequestHandlerSelectors.basePackage("com.outmao.ebs"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("api文档")
				.description("api文档")
				.termsOfServiceUrl("http://outmao.com/")
				.version("1.0")
				.build();
	}


}
