package sorisoop.soridam.api.like.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.like.application.LikeFacade;
import sorisoop.soridam.api.like.presentation.response.LikeInfoListResponse;
import sorisoop.soridam.domain.like.application.dto.LikeInfoDto;
import sorisoop.soridam.domain.like.domain.LikeType;

@RestController
@RequiredArgsConstructor
@Tag(name = "Like", description = "좋아요 API")
@RequestMapping("/api/likes")
public class LikeApiController {
	private final LikeFacade likeFacade;

	@Operation(summary = "좋아요 토글 API", description = "- Description : 해당 타겟에 대해 좋아요를 토글합니다.")
	@ApiResponse(responseCode = "200", description = "토글 성공")
	@PostMapping("/{likeType}/{targetId}")
	public ResponseEntity<Boolean> toggleLike(
		@Parameter(description = "좋아요 타입", example = "REVIEW", required = true)
		@PathVariable LikeType likeType,
		@Parameter(description = "타겟 ID", example = "1", required = true)
		@PathVariable long targetId
	) {
		boolean liked = likeFacade.toggleLike(likeType, targetId);
		return ResponseEntity.ok(liked);
	}

	@Operation(summary = "좋아요 정보 조회 API", description = "- Description : 특정 타겟에 대해 좋아요 수 및 좋아요 여부를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "조회 성공")
	@GetMapping("/{likeType}/{targetId}")
	public ResponseEntity<LikeInfoDto> getLikeInfo(
		@Parameter(description = "좋아요 타입", example = "REVIEW", required = true)
		@PathVariable LikeType likeType,
		@Parameter(description = "타겟 ID", example = "1", required = true)
		@PathVariable long targetId
	) {
		return ResponseEntity.ok(likeFacade.getLikeInfo(likeType, targetId));
	}

	@Operation(summary = "좋아요 리스트 정보 조회 API", description = "- Description : 여러 타겟 ID에 대한 좋아요 수 및 좋아요 여부를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "조회 성공")
	@GetMapping("/batch")
	public ResponseEntity<LikeInfoListResponse> getLikedMap(
		@Parameter(description = "좋아요 타입", example = "REVIEW", required = true)
		@RequestParam LikeType likeType,
		@Parameter(description = "타겟 ID 리스트", example = "1,2,3", required = true)
		@RequestParam List<Long> targetIds
	) {
		return ResponseEntity.ok(likeFacade.getLikedMap(likeType, targetIds));
	}
}
