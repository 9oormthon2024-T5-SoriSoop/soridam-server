package sorisoop.soridam.api.review.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.review.presentation.request.ReviewCreateRequest;
import sorisoop.soridam.api.review.presentation.request.ReviewUpdateRequest;
import sorisoop.soridam.api.review.presentation.response.ReviewListResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewPersistResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewResponse;
import sorisoop.soridam.domain.review.application.ReviewCommandService;
import sorisoop.soridam.domain.review.application.ReviewQueryService;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class ReviewFacade {
	private final ReviewCommandService reviewCommandService;
	private final ReviewQueryService reviewQueryService;
	private final UserQueryService userQueryService;

	@Transactional
	public ReviewPersistResponse create(ReviewCreateRequest request) {
		User author = userQueryService.me();

		Review review = reviewCommandService.create(
			request.targetId(),
			request.reviewType(),
			author,
			request.content(),
			request.rating()
		);

		return ReviewPersistResponse.from(review);
	}

	@Transactional
	public void update(String reviewId, ReviewUpdateRequest request) {
		User user = userQueryService.me();
		Review review = reviewQueryService.getById(reviewId);
		reviewCommandService.update(user, review, request.content(), request.rating());
	}

	@Transactional
	public void delete(String reviewId) {
		User user = userQueryService.me();
		Review review = reviewQueryService.getById(reviewId);
		reviewCommandService.delete(user, review);
	}

	@Transactional(readOnly = true)
	public ReviewListResponse getReviews(String targetId) {
		List<Review> reviews = reviewQueryService.getByTargetIdAndReviewType(targetId);
		List<ReviewResponse> responses = reviews.stream()
			.map(ReviewResponse::from)
			.toList();

		return ReviewListResponse.of(responses);
	}
}
