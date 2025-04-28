package sorisoop.soridam.api.review;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import sorisoop.soridam.api.review.application.ReviewFacade;
import sorisoop.soridam.api.review.presentation.request.ReviewCreateRequest;
import sorisoop.soridam.api.review.presentation.request.ReviewUpdateRequest;
import sorisoop.soridam.api.review.presentation.response.ReviewListResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewPersistResponse;
import sorisoop.soridam.domain.review.domain.ReviewType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "리뷰 API")
public class ReviewApiController {
	private final ReviewFacade reviewFacade;

	@Operation(summary = "리뷰 생성 API", description = "리뷰를 생성합니다.")
	@ApiResponse(responseCode = "201", description = "리뷰 생성 성공")
	@PostMapping
	public ResponseEntity<ReviewPersistResponse> createReview(
		@Valid @RequestBody ReviewCreateRequest request) {
		ReviewPersistResponse response = reviewFacade.create(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "리뷰 업데이트 API", description = "리뷰를 업데이트합니다.")
	@ApiResponse(responseCode = "200", description = "리뷰 업데이트 성공")
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateReview(
		@Parameter(description = "리뷰 ID", example = "123", required = true)
		@PathVariable Long id,
		@Valid @RequestBody ReviewUpdateRequest request) {
		reviewFacade.update(id, request);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "리뷰 삭제 API", description = "리뷰를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "리뷰 삭제 성공")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(
		@Parameter(description = "리뷰 ID", example = "123", required = true)
		@PathVariable Long id) {
		reviewFacade.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "리뷰 조회 API", description = "리뷰를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "리뷰 조회 성공")
	@GetMapping("/{targetId}")
	public ResponseEntity<ReviewListResponse> getReviews(
		@Parameter(description = "리뷰 타겟 ID", example = "123", required = true)
		@PathVariable Long targetId,
		@Parameter(description = "대상 종류 (ex: NOISE, ADDRESS 등)", required = true, example = "NOISE")
		@RequestParam ReviewType type
	) {
		ReviewListResponse response = reviewFacade.getReviews(targetId, type);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "대상 ID 목록 기반 리뷰 조회 API", description = """
    - Description : 여러 대상 ID와 리뷰 타입을 기반으로 리뷰를 조회합니다.
""")
	@ApiResponse(responseCode = "200", description = "리뷰 조회 성공")
	@GetMapping("/by-target-ids")
	public ResponseEntity<ReviewListResponse> getReviewsByTargetIds(
		@Parameter(description = "리뷰를 조회할 대상 ID 리스트", required = true, example = "[1,2,3]")
		@RequestParam List<Long> targetIds,

		@Parameter(description = "대상 종류 (ex: NOISE, ADDRESS 등)", required = true)
		@RequestParam ReviewType type
	) {
		ReviewListResponse response = reviewFacade.getReviewsByTargetIds(targetIds, type);
		return ResponseEntity.ok(response);
	}

}

