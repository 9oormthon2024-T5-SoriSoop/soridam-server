package sorisoop.soridam.api.noise.presentation.request;

import java.util.List;

public record NoiseSearchListRequest(
	List<NoiseSearchRequest> noiseSearchRequests
) {
}
