package com.soridam.server.user.controller;

import com.soridam.server.noise.dto.request.NoiseCreateRequest;
import com.soridam.server.noise.dto.response.NoiseCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soridam.server.noise.dto.response.NoiseSummaryListResponse;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.noise.service.NoiseService;
import com.soridam.server.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
	private final UserService userService;
	private final NoiseService noiseService;

	@GetMapping("/{id}")
	public ResponseEntity<NoiseSummaryListResponse> getUserNoises(
		@PathVariable Long id
	) {
		NoiseSummaryListResponse response = userService.getUserNoises(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/noise/{id}")
	public ResponseEntity<NoiseSummaryResponse> getUserDetailNoise(
		@PathVariable Long id
	) {
		NoiseSummaryResponse response = noiseService.getNoise(id);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<NoiseCreateResponse> createNoise(@RequestBody NoiseCreateRequest noiseCreateRequest) {
		Long noiseId = noiseService.createNoise(noiseCreateRequest);
		NoiseCreateResponse response = new NoiseCreateResponse(200, "success", noiseId);
		return ResponseEntity.ok(response);
	}
}
