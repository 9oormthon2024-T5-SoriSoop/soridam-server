package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.place_service.noise.domain.Noise;

@Builder
public record NoiseSummaryResponse(
	@Schema(description = "소음 ID", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "작성자", example = "nninjo_on", requiredMode = REQUIRED)
	String author,

	@Schema(description = "평균 소음 데시벨", example = "50", requiredMode = REQUIRED)
	int avgDecibel,

	@Schema(description = "데이터 생성 시간", example = "2024년 12월 14일 21시 37분", requiredMode = REQUIRED)
	String createdAt
) {
	public static NoiseSummaryResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		return builder()
			.id(noise.getId())
			.author(noise.getUser().getNickname())
			.avgDecibel(noise.getAvgDecibel())
			.createdAt(formatter.format(noise.getCreatedAt()))
			.build();
	}
}
