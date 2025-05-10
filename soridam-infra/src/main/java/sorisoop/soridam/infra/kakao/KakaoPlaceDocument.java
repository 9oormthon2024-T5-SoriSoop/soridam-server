package sorisoop.soridam.infra.kakao;

public record KakaoPlaceDocument(
	String place_name,
	String place_url,
	String road_address_name,
	String address_name,
	String x,
	String y,
	String category_group_code
) {
}
