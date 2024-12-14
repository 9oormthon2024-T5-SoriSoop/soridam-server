package com.soridam.server.noise.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.soridam.server.noise.domain.Noise;

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
