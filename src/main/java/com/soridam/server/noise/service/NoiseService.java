package com.soridam.server.noise.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soridam.server.common.util.GeometryUtils;
import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.enums.NoiseLevel;
import com.soridam.server.noise.dto.enums.Radius;
import com.soridam.server.noise.dto.request.NoiseCreateRequest;
import com.soridam.server.noise.dto.request.NoiseSearchListRequest;
import com.soridam.server.noise.dto.response.NoiseDetailResponse;
import com.soridam.server.noise.dto.response.NoiseListResponse;
import com.soridam.server.noise.dto.response.NoisePersistResponse;
import com.soridam.server.noise.dto.response.NoiseResponse;
import com.soridam.server.noise.dto.response.NoiseReviewResponse;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.noise.exception.NoiseNotFoundException;
import com.soridam.server.noise.repository.JpaNoiseRepository;
import com.soridam.server.noise.repository.QueryNoiseRepository;
import com.soridam.server.user.domain.User;
import com.soridam.server.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;
	private final GeometryUtils geometryUtils;
  private final UserService userService;

  @Transactional(readOnly = true)
  public NoiseDetailResponse getDetailNoise(double x, double y) {
		Point point = geometryUtils.createPoint(x, y);
		List<Noise> results = queryNoiseRepository.getNearbyNoises(point);

		List<NoiseResponse> noises = results.stream()
			.map(NoiseResponse::from)
			.toList();

		List<NoiseReviewResponse> reviews = results.stream()
			.map(NoiseReviewResponse::from)
			.toList();

		return NoiseDetailResponse.of(noises, reviews);
  }	

	@Transactional(readOnly = true)
	public NoiseListResponse getNearbyNoise(NoiseSearchListRequest requests, Radius radius, NoiseLevel noiseLevel) {
		List<NoiseResponse> responses = requests.noiseSearchRequests().stream()
			.map(request -> {
				Point point = geometryUtils.createPoint(request.x(), request.y());
				List<Noise> results = queryNoiseRepository.findByAvgDecibleAndPoint(point, radius, noiseLevel);

				if (results.isEmpty()) {
					return null;
				}

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

		return NoiseListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public NoiseSummaryResponse getNoise(Long id) {
		Noise noise = jpaNoiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);
		return NoiseSummaryResponse.from(noise);
	}

	@Transactional
	public NoisePersistResponse createNoise(NoiseCreateRequest request) {
		User user = userService.getById(1L);
		Point point = geometryUtils.createPoint(request.x(), request.y());

		Noise noise = Noise.create(
				user,
				point,
				request.maximumDecibel(),
				request.averageDecibel(),
				request.review()
		);

		jpaNoiseRepository.save(noise);

		return NoisePersistResponse.of(noise.getId());
	}

	@Transactional
	public void deleteNoise(Long id) {
		if (!jpaNoiseRepository.existsById(id)) {
			throw new NoiseNotFoundException();
		}
		jpaNoiseRepository.deleteById(id);
	}
}
