package com.soridam.server.noise.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soridam.server.noise.dto.response.NoiseSummaryListResponse;
import com.soridam.server.noise.service.NoiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noise")
public class NoiseApiController {
	private final NoiseService noiseService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<NoiseSummaryListResponse> getUserNoises(
		@PathVariable Long userId
	) {
		NoiseSummaryListResponse response = noiseService.getUserNoises(userId);
		return ResponseEntity.ok(response);
	}
}
