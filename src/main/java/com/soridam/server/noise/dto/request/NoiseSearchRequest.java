package com.soridam.server.noise.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record NoiseSearchRequest(
	@Schema(description = "X 좌표 (경도)", example = "126.9780", requiredMode = REQUIRED)
	@NotNull
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.5665", requiredMode = REQUIRED)
	@NotNull
	double y
) {
}
