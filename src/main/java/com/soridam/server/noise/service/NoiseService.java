package com.soridam.server.noise.service;

import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.request.NoiseCreateRequest;
import com.soridam.server.user.domain.User;
import com.soridam.server.user.repository.JpaUserRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.soridam.server.noise.repository.JpaNoiseRepository;
import com.soridam.server.noise.repository.QueryNoiseRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;

	private final JpaNoiseRepository noiseRepository;
	private final GeometryFactory geometryFactory;
	private final JpaUserRepository jpaUserRepository;


	@Transactional
	public Long createNoise(NoiseCreateRequest noiseCreateRequest) {

		// UserRepository를 통해 userId에 해당하는 User 엔티티 조회
		User user = jpaUserRepository.findById(noiseCreateRequest.userId())
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
		// Point 객체 생성 (위도/경도를 PostGIS와 호환되게 생성)
		Point point = geometryFactory.createPoint(new Coordinate(
				noiseCreateRequest.x(),
				noiseCreateRequest.y()
		));

		// User 엔티티는 연관관계를 통해 NoiseBuilder에서 설정
		Noise noise = Noise.builder()
				.user(user)
				.point(point)
				.maxDecibel(noiseCreateRequest.maximumDecibel())
				.avgDecibel(noiseCreateRequest.averageDecibel())
				.review(noiseCreateRequest.review())
				.build();


		// Noise 저장
		Noise savedNoise = noiseRepository.save(noise);
		return savedNoise.getId();
	}
}