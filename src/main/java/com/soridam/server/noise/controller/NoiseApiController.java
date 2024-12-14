package com.soridam.server.noise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soridam.server.noise.dto.enums.NoiseLevel;
import com.soridam.server.noise.dto.enums.Radius;
import com.soridam.server.noise.dto.request.NoiseCreateRequest;
import com.soridam.server.noise.dto.request.NoiseSearchListRequest;
import com.soridam.server.noise.dto.response.NoiseCreateResponse;
import com.soridam.server.noise.dto.response.NoiseListResponse;
import com.soridam.server.noise.service.NoiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noise")
public class NoiseApiController {
	private final NoiseService noiseService;

  @PostMapping
  public ResponseEntity<NoiseCreateResponse> createNoise(
    @RequestBody NoiseCreateRequest noiseCreateRequest
  ) {
    Long noiseId = noiseService.createNoise(noiseCreateRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteNoise(
    @PathVariable Long id
  ) {
    noiseService.deleteNoise(id);
    return ResponseEntity.ok("Noise successfully deleted.");
  }
  
	@PostMapping("/nearby")
	public ResponseEntity<NoiseListResponse> getNearbyNoise(
		@RequestBody NoiseSearchListRequest request,
		@RequestParam(required = false, defaultValue = "FIVE_HUNDRED_METERS") Radius radius,
		@RequestParam(required = false, defaultValue = "QUIET") NoiseLevel level
	) {
		NoiseListResponse response = noiseService.getNearbyNoise(request, radius, level);
		return ResponseEntity.ok(response);
	}
}
