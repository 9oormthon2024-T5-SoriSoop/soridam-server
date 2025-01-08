package sorisoop.soridam.api.noise.application;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.request.NoiseSearchListRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseDetailResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseReviewResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.domain.noise.application.NoiseCommandService;
import sorisoop.soridam.domain.noise.application.NoiseQueryService;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.Radius;

@Component
@RequiredArgsConstructor
public class NoiseFacade {
	private final NoiseCommandService noiseCommandService;
	private final NoiseQueryService noiseQueryService;

	public Optional<NoiseDetailResponse> getDetailNoise(double x, double y) {
		List<Noise> results = noiseQueryService.getDetailNoise(x, y);

		if (results.isEmpty()) return Optional.empty();

		List<NoiseResponse> noises = results.stream()
			.map(NoiseResponse::from)
			.toList();

		List<NoiseReviewResponse> reviews = results.stream()
			.map(NoiseReviewResponse::from)
			.toList();

		return Optional.of(NoiseDetailResponse.of(noises, reviews));
	}

	public Optional<NoiseListResponse> getNearbyNoise(
		NoiseSearchListRequest requests, Radius radius, NoiseLevel noiseLevel) {
		List<NoiseResponse> responses = requests.noiseSearchRequests().stream()
			.map(request -> {
				double x = request.x();
				double y = request.y();

				List<Noise> results = noiseQueryService.getNearbyNoise(x, y, radius, noiseLevel);

				if (results.isEmpty()) return null;

				int avgDecibel = (int) results.stream()
					.mapToInt(Noise::getAvgDecibel)
					.average()
					.orElse(0);

				return NoiseResponse.of(x, y, avgDecibel);
			})
			.filter(Objects::nonNull)
			.sorted(Comparator.comparingInt(NoiseResponse::avgDecibel))
			.limit(3)
			.toList();

		if (responses.isEmpty()) return Optional.empty();

		return Optional.of(NoiseListResponse.of(responses));
	}

	public NoiseSummaryResponse getNoise(String id) {
		Noise noise = noiseQueryService.getNoise(id);
		return NoiseSummaryResponse.from(noise);
	}

	public NoisePersistResponse createNoise(NoiseCreateRequest request) {
		Noise noise = noiseCommandService.createNoise(
			request.x(), request.y(),
			request.maxDecibel(),
			request.avgDecibel(),
			request.review()
		);

		return NoisePersistResponse.from(noise);
	}

	@Transactional
	public void deleteNoise(String id) {
		noiseCommandService.deleteNoise(id);
	}
}
