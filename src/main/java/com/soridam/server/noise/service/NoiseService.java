package com.soridam.server.noise.service;

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

	@Transactional(readOnly = true)
	public NoiseSummaryResponse getNoise(Long id) {
		Noise noise = jpaNoiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);

		return NoiseSummaryResponse.from(noise);
	}
}
