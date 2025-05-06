package sorisoop.soridam.api.favoriteplace.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import sorisoop.soridam.api.favoriteplace.application.FavoritePlaceFacade;
import sorisoop.soridam.api.favoriteplace.presentation.request.FavoritePlaceCreateRequest;
import sorisoop.soridam.api.favoriteplace.presentation.response.FavoritePlacePersistResponse;
import sorisoop.soridam.api.favoriteplace.presentation.response.FavoritePlaceResponse;
import sorisoop.soridam.common.response.SliceResponse;

@RestController
@RequiredArgsConstructor
@Tag(name = "Favorite Place", description = "장소 즐겨찾기 API")
@RequestMapping("/api/favorite-places")
public class FavoritePlaceApiController {
	private final FavoritePlaceFacade favoritePlaceFacade;

	@Operation(summary = "장소 즐겨찾기 추가 API", description = """
			- Description : 이 API는 해당 장소를 즐겨찾기로 등록합니다.
		""")
	@ApiResponse(responseCode = "201")
	@PostMapping
	public ResponseEntity<FavoritePlacePersistResponse> create(
		@Valid @RequestBody FavoritePlaceCreateRequest request
	) {
		FavoritePlacePersistResponse response = favoritePlaceFacade.create(request);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "장소 즐겨찾기 삭제 API", description = """
			- Description : 이 API는 해당 장소를 즐겨찾기에서 제거합니다.
		""")
	@ApiResponse(responseCode = "204")
	@DeleteMapping("/{placeId}")
	public ResponseEntity<Void> delete(
		@Parameter(description = "삭제할 즐겨찾기 장소 ID", example = "1", required = true)
		@PathVariable Long placeId
	) {
		favoritePlaceFacade.deleteByUserIdAndPlaceId(placeId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "장소 즐겨찾기 리스트 조회 API", description = """
			- Description : 이 API는 해당 장소를 즐겨찾기로 등록합니다.
		""")
	@ApiResponse(responseCode = "200")
	@GetMapping
	public ResponseEntity<SliceResponse<FavoritePlaceResponse>> getByUserIdWithCursor(
		@RequestParam(required = false)
		@Parameter(description = "커서 페이징을 위한 마지막 즐겨찾기 Id", example = "1")
		String lastId,

		@RequestParam(defaultValue = "10")
		@Parameter(description = "가져올 데이터 개수", example = "10")
		int limit
	) {
		Long last = StringUtils.hasText(lastId) ? Long.parseLong(lastId) : null;
		return ResponseEntity.ok(favoritePlaceFacade.findByUserId(last, limit));
	}

}
