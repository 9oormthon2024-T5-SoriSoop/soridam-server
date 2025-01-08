package sorisoop.soridam.api.user.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.application.NoiseService;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.user.application.UserFacade;
import sorisoop.soridam.api.user.presentation.request.UserCreateRequest;
import sorisoop.soridam.api.user.presentation.response.UserPersistResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API")
public class UserApiController {
	private final UserFacade userFacade;
	private final NoiseService noiseService;

	@Operation(summary = "마이페이지 본인이 등록한 noise 데이터 조회 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/{userId}")
	public ResponseEntity<NoiseSummaryListResponse> getUserNoises(
		@Parameter(description = "조회할 사용자의 ID", example = "9f3b462d-0fe9-4e7a-ae5d-74f9d9fc3ba4")
		@PathVariable String userId
	) {
		NoiseSummaryListResponse response = userFacade.getUserNoises(userId);
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

	@Operation(summary = "유저 회원가입 API", description = """
			- Description : 이 API는 유저 회원가입입니다.
		""")
	@ApiResponse(responseCode = "201")
	@PostMapping("/signup")
	public ResponseEntity<UserPersistResponse> signUp(
		@Valid
		@RequestBody
		UserCreateRequest request
	) {
		UserPersistResponse response = userFacade.signUp(request);
		return ResponseEntity.status(CREATED).body(response);
	}
}
