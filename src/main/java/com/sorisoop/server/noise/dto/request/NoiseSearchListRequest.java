package com.sorisoop.server.noise.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record NoiseSearchListRequest(
	@NotNull
	List<NoiseSearchRequest> noiseSearchRequests
) {
}
