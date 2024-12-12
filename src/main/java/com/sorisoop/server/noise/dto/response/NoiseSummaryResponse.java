package com.sorisoop.server.noise.dto.response;

import java.time.format.DateTimeFormatter;

import com.sorisoop.server.noise.domain.Noise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseSummaryResponse(
	@NotNull
	Long id,

	@NotNull
	double x,

	@NotNull
	double y,

	@NotNull
	int averageDecibel,

	@NotNull
	int maximumDecibel,

	@NotBlank
	String createdAt,

	@NotBlank
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
