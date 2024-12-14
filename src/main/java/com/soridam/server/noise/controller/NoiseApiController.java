package com.soridam.server.noise.controller;

import com.soridam.server.noise.dto.request.NoiseCreateRequest;
import com.soridam.server.noise.dto.response.NoiseCreateResponse;
import com.soridam.server.noise.service.NoiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/noise")
public class NoiseApiController {
    private final NoiseService noiseService;

    @PostMapping
    public ResponseEntity<NoiseCreateResponse> createNoise(@RequestBody NoiseCreateRequest noiseCreateRequest) {
        Long noiseId = noiseService.createNoise(noiseCreateRequest);
        NoiseCreateResponse response = new NoiseCreateResponse(200, "success", noiseId);
        return ResponseEntity.ok(response);
    }

}