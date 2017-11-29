package fr.iagl.gamification;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("fr.iagl.gamification.controller"))
				.paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Startup Back REST API", "Liste REST URL", "API TOS", "Terms of service",
				new Contact("", "", ""), "Websocket informations",
				"https://docs.google.com/spreadsheets/d/143uYsnhLAgCt0MSIdNJSgmr0E2Uk8R3dLaRsguf0eDg/edit?usp=sharing",
				Collections.emptyList());
		return apiInfo;
	}
}
