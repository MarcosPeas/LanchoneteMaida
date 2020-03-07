package com.lanchonete.maida.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
	final String swaggerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZWFzLnNoYWRvd0BnbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9HRVNUT1IiLCJjcmVhdGVkIjoxNTgzNTczMDk5MDM4LCJleHAiOjE2MTUxMDkwOTl9.r4Oa6Oay_8mL-wvkRAoAKf5j29b6RoeEE3SYuJY7crvRdQQiuo0S-872ShK8DzTRe4LiHAUbLC0J2ozjSpKI2g";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(singletonList()).select()
				.apis(RequestHandlerSelectors.basePackage("com.lanchonete.maida")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private List<Parameter> singletonList() {
		Parameter build = new ParameterBuilder().name("Authorization").modelRef(new ModelRef("string"))
				.parameterType("header").required(true).hidden(false).defaultValue("Bearer " + swaggerToken).build();
		return Arrays.asList(build);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger API")
				.description("Documentação da API da Lanchonete Maida com Swagger").version("1.0").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
