package com.sorisoop.server.noise.dto.response;

import java.time.format.DateTimeFormatter;

import com.sorisoop.server.noise.domain.Noise;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseResponse(
	@NotNull
	double x,

	@NotNull
	double y,

	@NotNull
	int avgDecibel,

	@NotNull
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
}
