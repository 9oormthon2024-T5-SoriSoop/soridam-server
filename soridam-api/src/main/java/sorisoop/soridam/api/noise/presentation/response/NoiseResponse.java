package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import sorisoop.soridam.api.address.response.AddressResponse;
import sorisoop.soridam.domain.noise.domain.Noise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseResponse(
	@Schema(description = "소음 발생 지점 주소", requiredMode = REQUIRED)
	AddressResponse address,

	@Schema(description = "평균 소음 데시벨", example = "50", requiredMode = REQUIRED)
	int avgDecibel,

	@Schema(description = "데이터 생성 시간", example = "2024년 12월 14일 21시 37분", requiredMode = REQUIRED)
	String createdAt
) {
	public static NoiseResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		AddressResponse addressResponse = AddressResponse.from(noise.getAddress());
		return builder()
			.address(addressResponse)
			.avgDecibel(noise.getAvgDecibel())
			.createdAt(formatter.format(noise.getCreatedAt()))
			.build();
	}
}
