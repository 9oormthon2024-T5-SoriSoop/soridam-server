package sorisoop.soridam.api.address.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AddressListResponse(
	@Schema(
		description = "주소 응답 리스트",
		example = """
        [
          {
            "x": 126.9780,
            "y": 37.5665,
            "roadAddress": "서울특별시 중구 세종대로 110",
            "regionAddress": "서울특별시 중구 태평로1가",
            "category": "MT1"
          },
          {
            "x": 127.1234,
            "y": 37.4567,
            "roadAddress": "서울특별시 강남구 테헤란로 212",
            "regionAddress": "서울특별시 강남구 역삼동",
            "category": "CE7"
          }
        ]
        """,
		requiredMode = REQUIRED
	)
	List<AddressResponse> responses
) {
	public static AddressListResponse of(List<AddressResponse> responses) {
		return AddressListResponse.builder()
			.responses(responses)
			.build();
	}
}
