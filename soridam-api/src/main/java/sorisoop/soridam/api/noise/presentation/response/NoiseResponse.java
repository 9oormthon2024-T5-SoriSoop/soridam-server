package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import sorisoop.soridam.api.place.presentation.response.PlaceResponse;
import sorisoop.soridam.domain.noise.domain.Noise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.api.noise.presentation.enums.NoiseLevel;

@Builder
public record NoiseResponse(
	@Schema(description = "소음 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "소음 발생 지점 주소", requiredMode = REQUIRED)
	PlaceResponse place,

	@Schema(description = "평균 데시벨", example = "50", requiredMode = REQUIRED)
	int avgDecibel,

	@Schema(description = "최대 데시벨", example = "70", requiredMode = REQUIRED)
	int maxDecibel,

	@Schema(description = "작성일자", example = "2024년 12월 14일 21시 37분", requiredMode = REQUIRED)
	String createdAt,

	@Schema(description = "소음 레벨", example = "NOLMAL", requiredMode = REQUIRED)
	NoiseLevel noiseLevel
) {
	public static NoiseResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		PlaceResponse placeResponse = PlaceResponse.from(noise.getPlace());
		return builder()
			.id(noise.getId())
			.place(placeResponse)
			.avgDecibel(noise.getAvgDecibel())
			.maxDecibel(noise.getMaxDecibel())
			.createdAt(noise.getCreatedAt().format(formatter))
			.noiseLevel(NoiseLevel.from(noise.getAvgDecibel()))
			.build();
	}
}
