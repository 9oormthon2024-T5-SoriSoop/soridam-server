package com.soridam.server.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soridam.server.noise.dto.response.NoiseSummaryListResponse;
import com.soridam.server.noise.dto.response.NoiseSummaryResponse;
import com.soridam.server.noise.service.NoiseService;
import com.soridam.server.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
	private final UserService userService;
	private final NoiseService noiseService;

	@Operation(summary = "마이페이지 본인이 등록한 noise 데이터 조회 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/{userId}")
	public ResponseEntity<NoiseSummaryListResponse> getUserNoises(
		@PathVariable Long userId
	) {
		NoiseSummaryListResponse response = userService.getUserNoises(userId);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "마이페이지 본인이 등록한 noise 데이터 중 선택 데이터 조회 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/noise/{noiseId}")
	public ResponseEntity<NoiseSummaryResponse> getUserNoiseDetail(
		@PathVariable Long noiseId
	) {
		NoiseSummaryResponse response = noiseService.getNoise(noiseId);
		return ResponseEntity.ok(response);
	}
}
