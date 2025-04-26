package sorisoop.soridam.api.noise.application;

import static sorisoop.soridam.globalutil.uuid.UuidPrefix.NOISE;
import static sorisoop.soridam.globalutil.uuid.UuidPrefix.USER;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.request.NoiseSearchRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseReviewResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewResponse;
import sorisoop.soridam.domain.address.application.AddressQueryService;
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
	private static final String NOISE_PREFIX = NOISE.getPrefix();

	private final NoiseCommandService noiseCommandService;
	private final NoiseQueryService noiseQueryService;
	private final UserQueryService userQueryService;
	private final AddressQueryService addressQueryService;
	private final ReviewQueryService reviewQueryService;

	@Transactional(readOnly = true)
	public Optional<NoiseReviewResponse> getDetailNoise(String addressId) {
		List<Noise> results = noiseQueryService.getDetailNoise(addressId);

		List<String> resultIds = results.stream()
			.map(Noise::getId)
			.toList();

		if (results.isEmpty()) return Optional.empty();

		List<NoiseSummaryResponse> noises = results.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		List<ReviewResponse> reviews = reviewQueryService.getByTargetIdIn(resultIds).stream()
			.map(ReviewResponse::from)
			.toList();

		return Optional.of(NoiseReviewResponse.of(noises, reviews));
	}

	@Transactional(readOnly = true)
	public NoiseSummaryListResponse getNearbyNoise(
		NoiseSearchRequest requests, Radius radius, NoiseLevel noiseLevel) {
		List<NoiseSummaryResponse> responses = noiseQueryService.getNearbyNoise(requests.x(), requests.y(), radius, noiseLevel).stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		return NoiseSummaryListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public NoiseResponse getNoise(String id) {
		Noise noise = noiseQueryService.getNoise(NOISE_PREFIX + id);
		return NoiseResponse.from(noise);
	}

	@Transactional
	public NoisePersistResponse createNoise(NoiseCreateRequest request) {
		User user = userQueryService.me();
		Address address = addressQueryService.getById(request.addressId());

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
		noiseCommandService.deleteNoise(user, NOISE_PREFIX + id);
	}

	@Transactional(readOnly = true)
	public NoiseListResponse getNoisesByUserId(String userId) {
		List<Noise> noises = noiseQueryService.getNoisesByUserId(USER.getPrefix() + userId);
		List<NoiseResponse> responses = noises.stream()
			.map(NoiseResponse::from)
			.toList();

		return NoiseListResponse.of(responses);
	}
}
