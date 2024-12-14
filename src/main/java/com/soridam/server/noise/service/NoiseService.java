package com.soridam.server.noise.service;

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

import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.noise.exception.NoiseNotFoundException;
import com.soridam.server.noise.repository.JpaNoiseRepository;
import com.soridam.server.noise.repository.QueryNoiseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;
	private final JpaNoiseRepository noiseRepository;
	private final GeometryFactory geometryFactory;
	private final JpaUserRepository jpaUserRepository;

	@Transactional(readOnly = true)
	public NoiseSummaryResponse getNoise(Long id) {
		Noise noise = jpaNoiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);

		return NoiseSummaryResponse.from(noise);
	}

	@Transactional
	public Long createNoise(NoiseCreateRequest noiseCreateRequest) {

		// UserRepository를 통해 userId에 해당하는 User 엔티티 조회
		User user = jpaUserRepository.findById(noiseCreateRequest.userId())
				.orElseThrow(() -> new CustomException(UserExceptionCode.USER_NOT_FOUND));
		// Point 객체 생성 (위도/경도를 PostGIS와 호환되게 생성)
		Point point = geometryFactory.createPoint(new Coordinate(
				noiseCreateRequest.x(),
				noiseCreateRequest.y()
		));

		// Noise 생성 (create 정적 메서드 사용)
		Noise noise = Noise.create(
				user,
				point,
				noiseCreateRequest.maximumDecibel(),
				noiseCreateRequest.averageDecibel(),
				noiseCreateRequest.review()
		);

		// Noise 저장
		Noise savedNoise = noiseRepository.save(noise);
		return savedNoise.getId();
	}
}
