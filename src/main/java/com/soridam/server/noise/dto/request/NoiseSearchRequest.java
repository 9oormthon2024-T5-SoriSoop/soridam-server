package com.soridam.server.noise.dto.request;

import jakarta.validation.constraints.NotNull;

public record NoiseSearchRequest(
	@NotNull
	double x,

	@NotNull
	double y
) {
}
