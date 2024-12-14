package com.soridam.server.noise.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soridam.server.noise.dto.response.NoiseDetailResponse;
import com.soridam.server.noise.service.NoiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noise")
public class NoiseApiController {
	private final NoiseService noiseService;

	@GetMapping
	public ResponseEntity<NoiseDetailResponse> getDetailNoise(
		@PathVariable double x,
		@PathVariable double y
	){
		NoiseDetailResponse response = noiseService.getDetailNoise(x, y);
		return ResponseEntity.ok(response);
	}
}
