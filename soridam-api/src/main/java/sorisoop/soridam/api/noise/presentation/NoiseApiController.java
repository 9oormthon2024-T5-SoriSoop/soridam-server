package sorisoop.soridam.api.noise.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.application.NoiseFacade;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.common.response.SliceResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "Noise", description = "소음 API")
@RequestMapping("/api/noises")
public class NoiseApiController {
	private final NoiseFacade noiseFacade;

	@Operation(summary = "소음 데이터 조회 API", description = """
			- Description : 이 API는 해당 소음 데이터를 조회합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/{id}")
	public ResponseEntity<NoiseResponse> getUserNoiseDetail(
		@Parameter(description = "조회할 noise 데이터의 ID", example = "10", required = true)
		@PathVariable Long id
	) {
		NoiseResponse response = noiseFacade.getNoise(id);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "해당 장소에 대한 소음 데이터 조회", description = """
			- Description : 이 API는 선택한 장소에 대한 소음 데이터를 조회합니다.
		""")
	@ApiResponse(responseCode = "200", description = "요청 성공")
	@ApiResponse(responseCode = "204", description = "결과 없음")
	@GetMapping("/address/{addressId}")
	public ResponseEntity<SliceResponse<NoiseSummaryResponse>> getDetailNoise(
		@PathVariable Long addressId,
		@RequestParam(required = false) Long lastId,
		@RequestParam int limit
	){
		SliceResponse<NoiseSummaryResponse> response = noiseFacade.getNoisesByAddress(addressId, lastId, limit);
		return ResponseEntity.ok(response);
  	}

	@Operation(summary = "소음 데이터 생성 API", description = """
			- Description : 이 API는 소음 데이터를 생성합니다.
		""")
	@ApiResponse(responseCode = "201")
  	@PostMapping
  	public ResponseEntity<NoisePersistResponse> createNoise(
		  @Valid @RequestBody NoiseCreateRequest noiseCreateRequest
  	) {
    	NoisePersistResponse response = noiseFacade.createNoise(noiseCreateRequest);
    	return ResponseEntity.status(CREATED).body(response);
  	}

	@Operation(summary = "소음 데이터 삭제 API", description = """
			- Description : 이 API는 소음 데이터를 삭제합니다.
		""")
	@ApiResponse(responseCode = "204")
  	@DeleteMapping("/{id}")
  	public ResponseEntity<Void> deleteNoise(
		@Parameter(description = "삭제할 데이터의 ID", example = "1", required = true)
		@PathVariable Long id
  	) {
		noiseFacade.deleteNoise(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "본인이 등록한 noise 데이터 조회 API", description = """
			- Description : 이 API는 댓글을 수정합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping
	public ResponseEntity<NoiseListResponse> getUserNoises(
		@Parameter(description = "조회할 사용자의 ID", example = "9f3b462d-0fe9-4e7a-ae5d-74f9d9fc3ba4")
		@RequestParam Long userId
	) {
		NoiseListResponse response = noiseFacade.getNoisesByUserId(userId);
		return ResponseEntity.ok(response);
	}
}
