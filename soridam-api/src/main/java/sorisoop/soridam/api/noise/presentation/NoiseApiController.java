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
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.application.NoiseService;
import sorisoop.soridam.api.noise.presentation.request.NoiseCreateRequest;
import sorisoop.soridam.api.noise.presentation.request.NoiseSearchListRequest;
import sorisoop.soridam.api.noise.presentation.response.NoiseDetailResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoisePersistResponse;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.Radius;

@RestController
@RequiredArgsConstructor
@Tag(name = "Noise", description = "소음 API")
@RequestMapping("/api/noises")
public class NoiseApiController {
	private final NoiseService noiseService;

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
		Optional<NoiseListResponse> response = noiseService.getNearbyNoise(request, radius, level);
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
		Optional<NoiseDetailResponse> response = noiseService.getDetailNoise(x, y);

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
    	NoisePersistResponse response = noiseService.createNoise(noiseCreateRequest);
    	return ResponseEntity.status(CREATED).body(response);
  	}

	@Operation(summary = "소음 데이터 API", description = """
			- Description : 이 API는 소음 데이터를 삭제합니다.
		""")
	@ApiResponse(responseCode = "204")
  	@DeleteMapping("/{id}")
  	public ResponseEntity<Void> deleteNoise(
		@Parameter(description = "삭제할 데이터의 ID", example = "1", required = true)
		@PathVariable @Positive Long id
  	) {
    	noiseService.deleteNoise(id);
    	return ResponseEntity.noContent().build();
  	}
}
