package com.sorisoop.server.noise.dto.response;

import java.time.format.DateTimeFormatter;

import com.sorisoop.server.noise.domain.Noise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseResponse(
	@NotNull
	double x,

	@NotNull
	double y,

	@NotNull
	int measurementTime,

	@NotNull
	int averageDecibel,

	@NotNull
	int maximumDecibel,

	@NotBlank
	String userName,

	@NotBlank
	String createdAt

) {
	public static NoiseResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		return NoiseResponse.builder()
			.x(noise.getPoint().getX())
			.y(noise.getPoint().getY())
			.measurementTime(noise.getMeasurementTime())
			.averageDecibel(noise.getAvgDecibel())
			.maximumDecibel(noise.getMaxDecibel())
			.userName(noise.getUser().getName())
			.createdAt(noise.getCreatedAt().format(formatter))
			.build();
	}
}
