package com.soridam.server.noise.service;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import com.soridam.server.common.util.GeometryUtils;
import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.response.NoiseDetailResponse;
import com.soridam.server.noise.dto.response.NoiseResponse;
import com.soridam.server.noise.dto.response.NoiseReviewResponse;
import com.soridam.server.noise.repository.JpaNoiseRepository;
import com.soridam.server.noise.repository.QueryNoiseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;
	private final GeometryUtils geometryUtils;

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
}
