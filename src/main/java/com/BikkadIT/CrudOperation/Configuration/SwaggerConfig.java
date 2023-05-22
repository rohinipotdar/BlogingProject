package com.BikkadIT.CrudOperation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	    @Bean
	    public Docket apiDoc() {
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                .apis(RequestHandlerSelectors.basePackage("com.BikkadIT.CrudOperation")).paths(PathSelectors.any()).build();

	    }
	    
	    @SuppressWarnings("deprecation")
		private ApiInfo getInfo() {
	    	return new ApiInfo("Backend course: ", "This project is learned by Online", 
	    			"1.0", "Terms of services","Done by rohini" , "create crud operations", "Using Springboot Apis");
	    	
	    }
	}

