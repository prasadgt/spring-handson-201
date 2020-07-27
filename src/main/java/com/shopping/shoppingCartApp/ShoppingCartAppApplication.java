package com.shopping.shoppingCartApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class ShoppingCartAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartAppApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/cart/*"))
				.apis(RequestHandlerSelectors.basePackage("com.shopping")).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo(){
		return new ApiInfo("Cart Controller API","Handle crud operation for Cart operation","1.0","Free to use",
				new Contact("Prasad","http://8084/cart","Prasad@gmail.com"),
				"API License","http://localhost:8084", Collections.emptyList());
	}
}
