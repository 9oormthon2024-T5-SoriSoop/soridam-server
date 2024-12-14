package com.soridam.server.noise.dto.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.time.format.DateTimeFormatter;

import com.soridam.server.noise.domain.Noise;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseResponse(
	@Schema(description = "소음 발생 지점의 X좌표 (경도)", example = "127.015", requiredMode = REQUIRED)
	double x,

	@Schema(description = "소음 발생 지점의 Y좌표 (위도)", example = "37.5805", requiredMode = REQUIRED)
	double y,

	@Schema(description = "평균 소음 데시벨", example = "50", requiredMode = REQUIRED)
	int avgDecibel,

	@Schema(description = "데이터 생성 시간", example = "2024년 12월 14일 21시 37분", requiredMode = REQUIRED)
	String createdAt
) {
	public static NoiseResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		return NoiseResponse.builder()
			.x(noise.getPoint().getX())
			.y(noise.getPoint().getY())
			.avgDecibel(noise.getAvgDecibel())
			.createdAt(formatter.format(noise.getCreatedAt()))
			.build();
	}

	public static NoiseResponse of(double x, double y, int avgDecibel) {
		return NoiseResponse.builder()
			.x(x)
			.y(y)
			.avgDecibel(avgDecibel)
			.build();
	}
}
