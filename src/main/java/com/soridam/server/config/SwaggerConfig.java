package com.soridam.server.config;

import static java.lang.String.format;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
			.title("SORIDAM API")
			.description(getDescription());
	}


	private String getDescription() {
		return format("""
				주변 조용한 장소 찾기, SORIDAM API 입니다.\n\n
				별다른 절차 없이 API를 사용하실 수 있습니다.\n\n
				""");
	}
}
