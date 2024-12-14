package com.soridam.server.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soridam.server.noise.dto.response.NoiseSummaryListResponse;
import com.soridam.server.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
	private final UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<NoiseSummaryListResponse> getUserNoises(
		@PathVariable Long userId
	) {
		NoiseSummaryListResponse response = userService.getUserNoises(userId);
		return ResponseEntity.ok(response);
	}
}
