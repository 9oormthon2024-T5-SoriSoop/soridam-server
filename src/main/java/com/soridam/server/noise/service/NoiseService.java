package com.soridam.server.noise.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soridam.server.noise.domain.Noise;
import com.soridam.server.noise.dto.response.NoiseSummaryListResponse;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.noise.repository.JpaNoiseRepository;
import com.soridam.server.noise.repository.QueryNoiseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;

	@Transactional(readOnly = true)
	public NoiseSummaryListResponse getUserNoises(Long userId) {
		List<Noise> results = jpaNoiseRepository.findByUserId(userId);
		List<NoiseSummaryResponse> responses = results.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		return NoiseSummaryListResponse.of(responses);
	}
}
