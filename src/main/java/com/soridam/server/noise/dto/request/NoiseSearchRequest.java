package com.soridam.server.noise.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record NoiseSearchRequest(
	@Schema(description = "X 좌표 (경도)", example = "127.0711", requiredMode = REQUIRED)
	@NotNull
	double x,

	@Schema(description = "Y 좌표 (위도)", example = "37.34668", requiredMode = REQUIRED)
	@NotNull
	double y
) {
}
