package com.sorisoop.server.noise.service;

import org.springframework.stereotype.Service;

import com.sorisoop.server.noise.repository.JpaNoiseRepository;
import com.sorisoop.server.noise.repository.QueryNoiseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoiseService {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;
}
