package sorisoop.soridam.api.place.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
import sorisoop.soridam.api.place.application.PlaceFacade;
import sorisoop.soridam.api.place.presentation.request.PlaceCreateRequest;
import sorisoop.soridam.api.place.presentation.response.PlaceDetailResponse;
import sorisoop.soridam.api.place.presentation.response.PlaceListResponse;
import sorisoop.soridam.api.place.presentation.response.PlacePersistResponse;
import sorisoop.soridam.domain.place.domain.enums.Category;

@RestController
@RequiredArgsConstructor
@Tag(name = "Address", description = "장소 API")
@RequestMapping("/api/addresses")
public class PlaceApiController {
	private final PlaceFacade placeFacade;

	@Operation(summary = "id 기반 장소 조회 API", description = """
			- Description : 이 API는 id로 해당 장소를 조회합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping("/{id}")
	public ResponseEntity<PlaceDetailResponse> getById(
		@Parameter(description = "조회할 장소의 ID", example = "address-adsfadsf", required = true)
		@PathVariable Long id
	) {
		PlaceDetailResponse response = placeFacade.getById(id);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "도로명 주소 기반 장소 조회 또는 생성 API", description = """
			- Description : 이 API는 도로명 주소로 장소를 조회하고,
			존재하지 않으면 새로 데이터를 생성한 뒤 반환합니다.
		""")
	@ApiResponse(responseCode = "200")
	@PostMapping("/resolve")
	public ResponseEntity<PlacePersistResponse> getByRoadAddress(
		@Valid @RequestBody PlaceCreateRequest request
	) {
		PlacePersistResponse response = placeFacade.getOrCreate(request);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "장소 데이터 생성 API", description = """
			- Description : 이 API는 장소 데이터를 생성합니다.
		""")
	@ApiResponse(responseCode = "201")
	@PostMapping
	public ResponseEntity<PlacePersistResponse> create(
		@Valid @RequestBody PlaceCreateRequest request
	) {
		PlacePersistResponse response = placeFacade.create(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(
		summary = "주변 소음 검색 API",
		description = """
        - 사용자의 좌표를 기준으로 반경 내 소음 데이터를 조회합니다.
        - 거리, 소음 수준, 카테고리 필터를 적용할 수 있습니다.
        
        ✅ 주요 기능:
        - x, y 좌표를 기준으로 지정 반경 안의 소음 조회
        - 소음 수준(조용함/약간 시끄러움 등) 필터링
        - 선택한 카테고리(카페, 공원 등)별 필터링 가능
    """
	)
	@ApiResponse(responseCode = "200", description = "요청 성공")
	@ApiResponse(responseCode = "204", description = "결과 없음")
	@GetMapping("/nearby")
	public ResponseEntity<PlaceListResponse> getNearbyNoise(
		@RequestParam @Parameter(description = "현재 위치 X 좌표 (longitude)", example = "127.12345", required = true)
		double x,

		@RequestParam @Parameter(description = "현재 위치 Y 좌표 (latitude)", example = "37.12345", required = true)
		double y,

		@RequestParam(required = false, defaultValue = "100")
		@Parameter(description = "거리 검색 범위", example = "100", required = true)
		int distanceMeter,

		@RequestParam(required = false)
		@Parameter(description = "필터링할 카테고리 리스트", example = "[\"MT1\", \"CS2\"]")
		List<Category> categories
	) {
		return ResponseEntity.ok(placeFacade.getNearPlacesByPoint(x, y, distanceMeter, categories));
	}
}
