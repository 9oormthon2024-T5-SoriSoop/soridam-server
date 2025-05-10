package sorisoop.soridam.infra.kakao;

import java.util.List;

public record KakaoSearchResponse(
	List<KakaoPlaceDocument> documents,
	KakaoPlaceMeta meta
) {
}
