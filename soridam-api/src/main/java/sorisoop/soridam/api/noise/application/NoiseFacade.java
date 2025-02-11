package sorisoop.soridam.api.noise.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.request.NoiseSearchRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseDetailResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewResponse;
import sorisoop.soridam.domain.address.application.AddressCommandService;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.noise.application.NoiseCommandService;
import sorisoop.soridam.domain.noise.application.NoiseQueryService;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.Radius;
import sorisoop.soridam.domain.review.application.ReviewQueryService;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class NoiseFacade {
	private final NoiseCommandService noiseCommandService;
	private final NoiseQueryService noiseQueryService;
	private final UserQueryService userQueryService;
	private final AddressCommandService addressCommandService;
	private final ReviewQueryService reviewQueryService;

	public Optional<NoiseDetailResponse> getDetailNoise(double x, double y) {
		List<Noise> results = noiseQueryService.getDetailNoise(x, y);

		List<String> resultIds = results.stream()
			.map(Noise::getId)
			.toList();

		if (results.isEmpty()) return Optional.empty();

		List<NoiseResponse> noises = results.stream()
			.map(NoiseResponse::from)
			.toList();

		List<ReviewResponse> reviews = reviewQueryService.getByTargetIdIn(resultIds).stream()
			.map(ReviewResponse::from)
			.toList();

		return Optional.of(NoiseDetailResponse.of(noises, reviews));
	}

	public NoiseListResponse getNearbyNoise(
		NoiseSearchRequest requests, Radius radius, NoiseLevel noiseLevel) {
		List<NoiseResponse> responses = noiseQueryService.getNearbyNoise(requests.x(), requests.y(), radius, noiseLevel).stream()
			.map(NoiseResponse::from)
			.toList();

		return NoiseListResponse.of(responses);
	}

	public NoiseSummaryResponse getNoise(String id) {
		Noise noise = noiseQueryService.getNoise(id);
		return NoiseSummaryResponse.from(noise);
	}

	public NoisePersistResponse createNoise(NoiseCreateRequest request) {
		User user = userQueryService.me();
		Address address = addressCommandService.save(request.x(), request.y(), request.roadAddress(),
			request.regionAddress());
		Noise noise = noiseCommandService.createNoise(
			user,
			address,
			request.maxDecibel(),
			request.avgDecibel()
		);

		return NoisePersistResponse.from(noise);
	}

	@Transactional
	public void deleteNoise(String id) {
		User user = userQueryService.me();
		noiseCommandService.deleteNoise(user, id);
	}
}
