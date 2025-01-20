package sorisoop.soridam.api.review;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import sorisoop.soridam.api.review.presentation.response.ReviewPersistResponse;

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
	@PutMapping("/{reviewId}")
	public ResponseEntity<Void> updateReview(
		@Parameter(description = "리뷰 ID", example = "123", required = true)
		@PathVariable String reviewId,
		@Valid @RequestBody ReviewUpdateRequest request) {
		reviewFacade.update(reviewId, request);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "리뷰 삭제 API", description = "리뷰를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "리뷰 삭제 성공")
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> deleteReview(
		@Parameter(description = "리뷰 ID", example = "123", required = true)
		@PathVariable String reviewId) {
		reviewFacade.delete(reviewId);
		return ResponseEntity.noContent().build();
	}
}

