package com.soridam.server.noise.dto.request;

import java.util.List;

public record NoiseSearchListRequest(
	List<NoiseSearchRequest> noiseSearchRequests
) {
}
