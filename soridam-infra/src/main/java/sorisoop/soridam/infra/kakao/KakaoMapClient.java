package sorisoop.soridam.infra.kakao;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoMapClient {
	private final WebClient kakaoWebClient;

	public KakaoPlaceResponse searchPlaces(String category, double x, double y, int radius, int page) {
		return kakaoWebClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/v2/local/search/category.json")
				.queryParam("category_group_code", category)
				.queryParam("x", x)
				.queryParam("y", y)
				.queryParam("radius", radius)
				.queryParam("page", page)
				.queryParam("sort","distance")
				.build())
			.retrieve()
			.bodyToMono(KakaoPlaceResponse.class)
			.block();
	}
}
