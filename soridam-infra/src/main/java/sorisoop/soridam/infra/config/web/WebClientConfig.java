package sorisoop.soridam.infra.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	@Bean
	public WebClient kakaoWebClient(@Value("${kakao.api.url}") String baseUrl,
		@Value("${kakao.api.key}") String apiKey) {
		return WebClient.builder()
			.baseUrl(baseUrl) // https://dapi.kakao.com
			.defaultHeader("Authorization", "KakaoAK " + apiKey)
			.build();
	}

}
