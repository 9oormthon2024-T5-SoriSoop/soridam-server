package sorisoop.soridam.api.noise.application;

import static sorisoop.soridam.common.uuid.UuidPrefix.NOISE;
import static sorisoop.soridam.common.uuid.UuidPrefix.USER;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sorisoop.soridam.api.noise.presentation.exception.NoiseNotFoundException;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.request.NoiseSearchListRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseDetailResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseReviewResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.user.application.UserService;
import sorisoop.soridam.common.util.GeometryUtils;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.Radius;
import sorisoop.soridam.domain.noise.infrastructure.JpaNoiseRepository;
import sorisoop.soridam.domain.noise.infrastructure.QueryNoiseRepository;
import sorisoop.soridam.domain.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;
	private final GeometryUtils geometryUtils;
	private final UserService userService;

  @Transactional(readOnly = true)
  public Optional<NoiseDetailResponse> getDetailNoise(double x, double y) {
		Point point = createPoint(x, y);
		List<Noise> results = queryNoiseRepository.getNearbyNoises(point);

		if (results.isEmpty()) return Optional.empty();

		List<NoiseResponse> noises = results.stream()
			.map(NoiseResponse::from)
			.toList();

		List<NoiseReviewResponse> reviews = results.stream()
			.map(NoiseReviewResponse::from)
			.toList();

		return Optional.of(NoiseDetailResponse.of(noises, reviews));
  }

	@Transactional(readOnly = true)
	public Optional<NoiseListResponse> getNearbyNoise(NoiseSearchListRequest requests, Radius radius, NoiseLevel noiseLevel) {
		List<NoiseResponse> responses = requests.noiseSearchRequests().stream()
			.map(request -> {
				Point point = createPoint(request.x(), request.y());
				List<Noise> results = queryNoiseRepository.findByAvgDecibleAndPoint(point, radius, noiseLevel);

				if (results.isEmpty()) return null;

				int avgDecibel = (int) results.stream()
					.mapToInt(Noise::getAvgDecibel)
					.average()
					.orElse(0);

				return NoiseResponse.of(point.getX(), point.getY(), avgDecibel);
			})
			.filter(Objects::nonNull)
			.sorted(Comparator.comparingInt(NoiseResponse::avgDecibel))
			.limit(3)
			.toList();

		if (responses.isEmpty()) return Optional.empty();

		return Optional.of(NoiseListResponse.of(responses));
	}

	@Transactional(readOnly = true)
	public NoiseSummaryResponse getNoise(String id) {
		Noise noise = jpaNoiseRepository.findById(NOISE.getPrefix() + id)
			.orElseThrow(NoiseNotFoundException::new);
		return NoiseSummaryResponse.from(noise);
	}

	@Transactional
	public NoisePersistResponse createNoise(NoiseCreateRequest request) {
		User user = userService.getById(USER.getPrefix() + "qnpranpswnps12@");
		Point point = createPoint(request.x(), request.y());

		Noise noise = Noise.create(
				user,
				point,
				request.maxDecibel(),
				request.avgDecibel(),
				request.review()
		);

		jpaNoiseRepository.save(noise);
		return NoisePersistResponse.from(noise);
	}

	@Transactional
	public void deleteNoise(String id) {
		if (!jpaNoiseRepository.existsById(id)) {
			throw new NoiseNotFoundException();
		}
		jpaNoiseRepository.deleteById(id);
	}

	private Point createPoint(double x, double y) {
		return geometryUtils.createPoint(x, y);
	}
}
