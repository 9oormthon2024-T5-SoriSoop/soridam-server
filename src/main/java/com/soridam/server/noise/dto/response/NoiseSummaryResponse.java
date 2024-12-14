package com.soridam.server.noise.dto.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import com.soridam.server.noise.domain.Noise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseSummaryResponse(
	@Schema(description = "소음 ID", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "X 좌표 (경도)", example = "126.9780", requiredMode = REQUIRED)
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.5665", requiredMode = REQUIRED)
	double y,

	@Schema(description = "평균 데시벨", example = "50", requiredMode = REQUIRED)
	int averageDecibel,

	@Schema(description = "최대 데시벨", example = "70", requiredMode = REQUIRED)
	int maximumDecibel,

	@Schema(description = "작성일자", example = "2024년 12월 14일 21시 37분", requiredMode = REQUIRED)
	String createdAt,

	@Schema(description = "리뷰 내용", example = "소음이 많은 환경", requiredMode = REQUIRED)
	String review
) {
	public static NoiseSummaryResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		return NoiseSummaryResponse.builder()
			.id(noise.getId())
			.x(noise.getPoint().getX())
			.y(noise.getPoint().getY())
			.averageDecibel(noise.getAvgDecibel())
			.maximumDecibel(noise.getMaxDecibel())
			.createdAt(noise.getCreatedAt().format(formatter))
			.review(noise.getReview())
			.build();
	}
}
