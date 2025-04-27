package sorisoop.soridam.api.review;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.review.application.ReviewFacade;
import sorisoop.soridam.api.review.presentation.request.ReviewCreateRequest;
import sorisoop.soridam.api.review.presentation.request.ReviewUpdateRequest;
import sorisoop.soridam.api.review.presentation.response.ReviewListResponse;
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
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateReview(
		@Parameter(description = "리뷰 ID", example = "123", required = true)
		@PathVariable String id,
		@Valid @RequestBody ReviewUpdateRequest request) {
		reviewFacade.update(id, request);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "리뷰 삭제 API", description = "리뷰를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "리뷰 삭제 성공")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(
		@Parameter(description = "리뷰 ID", example = "123", required = true)
		@PathVariable String id) {
		reviewFacade.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "리뷰 조회 API", description = "리뷰를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "리뷰 조회 성공")
	@GetMapping("/{targetId}")
	public ResponseEntity<ReviewListResponse> getReviews(
		@Parameter(description = "리뷰 타겟 ID", example = "123", required = true)
		@PathVariable Long targetId) {
		ReviewListResponse response = reviewFacade.getReviews(targetId);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Noise 리스트 기반 리뷰 조회 API", description = """
        - Description : Noise 리스트로부터 targetId를 추출해서 리뷰들을 조회합니다.
    """)
	@ApiResponse(responseCode = "200", description = "리뷰 조회 성공")
	@PostMapping("/by-noise-summaries")
	public ResponseEntity<ReviewListResponse> getReviewsByNoiseSummaries(
		@RequestBody List<NoiseSummaryResponse> noiseSummaries
	) {
		List<Long> resultIds = noiseSummaries.stream()
			.map(NoiseSummaryResponse::id)
			.toList();

		ReviewListResponse response = reviewFacade.getReviewsByTargetIds(resultIds);
		return ResponseEntity.ok(response);
	}
}

