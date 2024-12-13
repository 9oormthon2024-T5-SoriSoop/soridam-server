package com.soridam.server.noise.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record NoiseCreateRequest(
	@NotNull
	double x,
	
	@NotNull
	double y,

	@NotNull
	@Positive
	int averageDecibel,

	@NotNull
	@Positive
	int maximumDecibel,

	@NotBlank
	String review
) {
}
