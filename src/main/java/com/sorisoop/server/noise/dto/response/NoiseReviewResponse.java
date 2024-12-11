package com.sorisoop.server.noise.dto.response;

import java.time.format.DateTimeFormatter;

import com.sorisoop.server.noise.domain.Noise;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record NoiseReviewResponse(
	@NotBlank
	String userName,

	@NotBlank
	String review,

	@NotBlank
	String createdAt

) {
	public static NoiseReviewResponse from(Noise noise) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
		return NoiseReviewResponse.builder()
			.userName(noise.getUser().getName())
			.review(noise.getReview())
			.createdAt(noise.getCreatedAt().format(formatter))
			.build();
	}
}
