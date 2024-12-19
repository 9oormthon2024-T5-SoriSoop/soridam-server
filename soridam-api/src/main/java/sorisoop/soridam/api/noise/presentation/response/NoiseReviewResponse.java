package sorisoop.soridam.api.noise.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import sorisoop.soridam.domain.noise.domain.Noise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseReviewResponse(
	@Schema(description = "사용자 이름", example = "JohnDoe", requiredMode = REQUIRED)
	String userName,

	@Schema(description = "리뷰 내용", example = "소음이 심한 환경이었습니다.", requiredMode = REQUIRED)
	String review,

	@Schema(description = "리뷰 작성 시간", example = "2024년 12월 14일 21시 37분", requiredMode = REQUIRED)
	String createdAt
) {
	public static NoiseReviewResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		return builder()
			.userName(noise.getUser().getName())
			.review(noise.getReview())
			.createdAt(noise.getCreatedAt().format(formatter))
			.build();
	}
}
