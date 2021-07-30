package com.cognixia.jump.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * On Browser, go to URL: http://localhost:8080/swagger-ui/index.html
 * 
 * On Postman, do a get request to: http://localhost:8080/v2/api-docs
 * 
 * */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
	
	private ApiInfo apiDetails() {
		
		return new ApiInfo("Restaurant API", 
				"API for restaurants and reviews", 
				"1.0", 
				"For team 2 final project", // terms of service (url or message) 
				new Contact("Team2", 
							"https://github.com/JumpTeam2-FinalProject/back-end", 
							"team2@cognixia.com"),
				"MIT", // license 
				"https://github.com/JumpTeam2-FinalProject/back-end", // license url
				Collections.emptyList() // vendors
			);
		
	}
}