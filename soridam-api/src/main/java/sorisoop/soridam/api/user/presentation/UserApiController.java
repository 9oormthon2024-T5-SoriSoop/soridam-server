package sorisoop.soridam.api.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sorisoop.soridam.api.noise.application.NoiseService;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.user.application.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API")
public class UserApiController {
	private final UserService userService;
	private final NoiseService noiseService;

	@Operation(summary = "마이페이지 본인이 등록한 noise 데이터 조회 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/{userId}")
	public ResponseEntity<NoiseSummaryListResponse> getUserNoises(
		@Parameter(description = "조회할 사용자의 ID", example = "1")
		@PathVariable String userId
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
		@Parameter(description = "조회할 noise 데이터의 ID", example = "10", required = true)
		@PathVariable String noiseId
	) {
		NoiseSummaryResponse response = noiseService.getNoise(noiseId);
		return ResponseEntity.ok(response);
	}
}
