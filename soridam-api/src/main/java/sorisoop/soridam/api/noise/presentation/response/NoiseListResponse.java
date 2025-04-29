package sorisoop.soridam.api.noise.presentation.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseListResponse(
	@Schema(
		description = "소음 요약 정보 리스트",
		example = """
    [
      {
        "id": 1001,
        "address": {
          "x": 126.9780,
          "y": 37.5665,
          "roadAddress": "서울특별시 동대문구 장한로 110 (장안동)",
          "regionAddress": "서울특별시 동대문구 장안동 366-7",
          "category": "MT1"
        },
        "avgDecibel": 52,
        "maxDecibel": 70,
        "createdAt": "2024년 12월 14일 21시 37분"
      },
      {
        "id": 1000,
        "address": {
          "x": 127.1234,
          "y": 37.4567,
          "roadAddress": "서울특별시 강남구 테헤란로 212",
          "regionAddress": "서울특별시 강남구 역삼동 212",
          "category": "CE7"
        },
        "avgDecibel": 64,
        "maxDecibel": 82,
        "createdAt": "2024년 12월 14일 20시 15분"
      }
    ]
    """
	)
	List<NoiseResponse> responses
) {
	public static NoiseListResponse of(List<NoiseResponse> responses) {
		return builder()
			.responses(responses)
			.build();
	}
}
