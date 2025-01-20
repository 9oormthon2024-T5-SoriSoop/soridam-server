package sorisoop.soridam.api.review.application;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.review.presentation.request.ReviewCreateRequest;
import sorisoop.soridam.api.review.presentation.request.ReviewUpdateRequest;
import sorisoop.soridam.api.review.presentation.response.ReviewPersistResponse;
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

	public ReviewPersistResponse create(ReviewCreateRequest request) {
		User author = userQueryService.me();

		Review review = reviewCommandService.create(
			request.targetId(),
			request.reviewType(),
			author.getId(),
			request.content(),
			request.rating()
		);

		return ReviewPersistResponse.from(review);
	}

	public void update(String reviewId, ReviewUpdateRequest request) {
		Review review = reviewQueryService.getById(reviewId);
		reviewCommandService.update(review, request.content(), request.rating());
	}

	public void delete(String reviewId) {
		User user = userQueryService.me();
		reviewCommandService.delete(user, reviewId);
	}
}
