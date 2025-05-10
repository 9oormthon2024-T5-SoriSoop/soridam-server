package sorisoop.soridam.infra.kakao;

import java.util.List;

import lombok.Getter;

@Getter
public class KakaoPlaceResponse {
	private List<KakaoPlace> documents;
	private Meta meta;

	@Getter
	public static class Meta {
		private boolean is_end;
	}

	@Getter
	public static class KakaoPlace {
		private String place_name;
		private String road_address_name;
		private String address_name;
		private String category_group_code;
		private String place_url;
		private String x;
		private String y;
	}
}

