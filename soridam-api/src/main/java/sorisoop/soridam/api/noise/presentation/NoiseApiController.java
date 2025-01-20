package sorisoop.soridam.api.noise.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Optional;

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
import sorisoop.soridam.api.noise.presentation.request.NoiseSearchListRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseDetailResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.Radius;

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
	@GetMapping("/{noiseId}")
	public ResponseEntity<NoiseSummaryResponse> getUserNoiseDetail(
		@Parameter(description = "조회할 noise 데이터의 ID", example = "10", required = true)
		@PathVariable String noiseId
	) {
		NoiseSummaryResponse response = noiseFacade.getNoise(noiseId);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "주변 소음 조회 API", description = """
			- Description : 이 API는 주변 소음을 조회합니다.
		""")
	@ApiResponse(responseCode = "200", description = "요청 성공")
	@ApiResponse(responseCode = "204", description = "결과 없음")
	@PostMapping("/nearby")
	public ResponseEntity<NoiseListResponse> getNearbyNoise(
		@Valid @RequestBody NoiseSearchListRequest request,

		@RequestParam(required = false, defaultValue = "FIVE_HUNDRED_METERS")
		@Parameter(description = "거리 검색 범위", example = "FIVE_HUNDRED_METERS", required = true)
		Radius radius,

		@RequestParam(required = false, defaultValue = "QUIET")
		@Parameter(description = "소음 검색 범위", example = "QUIET", required = true)
		NoiseLevel level
	) {
		Optional<NoiseListResponse> response = noiseFacade.getNearbyNoise(request, radius, level);
		return response
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.noContent().build());
	}

	@Operation(summary = "주변 소음 검색 후 마커 선택 장소 조회 API", description = """
			- Description : 이 API는 주변 소음 검색 후 마커 선택 장소 조회를 조회합니다.
		""")
	@ApiResponse(responseCode = "200", description = "요청 성공")
	@ApiResponse(responseCode = "204", description = "결과 없음")
	@GetMapping
	public ResponseEntity<NoiseDetailResponse> getDetailNoise(
		@RequestParam
		@Parameter(description = "x 좌표", example = "127.07150", required = true)
		double x,

		@RequestParam
		@Parameter(description = "y 좌표", example = "37.3405", required = true)
		double y
	){
		Optional<NoiseDetailResponse> response = noiseFacade.getDetailNoise(x, y);

		return response
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.noContent().build());
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
		@PathVariable String id
  	) {
		noiseFacade.deleteNoise(id);
		return ResponseEntity.noContent().build();
	}


}
