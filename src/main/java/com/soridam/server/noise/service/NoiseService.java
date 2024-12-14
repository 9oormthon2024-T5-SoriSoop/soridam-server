package com.soridam.server.noise.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.locationtech.jts.geom.Point;
import com.soridam.server.common.exception.CustomException;
import com.soridam.server.noise.dto.request.NoiseCreateRequest;
import com.soridam.server.user.domain.User;
import com.soridam.server.user.exception.UserExceptionCode;
import com.soridam.server.user.repository.JpaUserRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soridam.server.common.util.GeometryUtils;
import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.enums.NoiseLevel;
import com.soridam.server.noise.dto.enums.Radius;
import com.soridam.server.noise.dto.request.NoiseSearchListRequest;
import com.soridam.server.noise.dto.response.NoiseListResponse;
import com.soridam.server.noise.dto.response.NoiseResponse;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.noise.exception.NoiseNotFoundException;
import com.soridam.server.noise.repository.JpaNoiseRepository;
import com.soridam.server.noise.repository.QueryNoiseRepository;
import com.soridam.server.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;
	private final GeometryUtils geometryUtils;

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
	public Long createNoise(NoiseCreateRequest noiseCreateRequest) {
		User user = jpaUserRepository.findById(noiseCreateRequest.userId())
				.orElseThrow(UserNotFoundException::new);
    
		Point point = geometryFactory.createPoint(new Coordinate(
				noiseCreateRequest.x(),
				noiseCreateRequest.y()
		));

		Noise noise = Noise.create(
				user,
				point,
				noiseCreateRequest.maximumDecibel(),
				noiseCreateRequest.averageDecibel(),
				noiseCreateRequest.review()
		);

		Noise savedNoise = noiseRepository.save(noise);
		return savedNoise.getId();
	}
}
