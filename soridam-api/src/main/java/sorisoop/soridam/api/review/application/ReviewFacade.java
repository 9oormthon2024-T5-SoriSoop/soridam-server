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
import sorisoop.soridam.domain.review.domain.ReviewType;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.infra.config.data.redis.event.ReviewCreatedEvent;
import sorisoop.soridam.infra.config.data.redis.event.ReviewEventPublisher;

@Component
@RequiredArgsConstructor
public class ReviewFacade {
	private final ReviewCommandService reviewCommandService;
	private final ReviewQueryService reviewQueryService;
	private final UserQueryService userQueryService;
	private final ReviewEventPublisher reviewEventPublisher;

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

		ReviewCreatedEvent event = ReviewCreatedEvent.from(review);
		reviewEventPublisher.publishReviewEvent(event);
		return ReviewPersistResponse.from(review);
	}

	@Transactional
	public void update(Long id, ReviewUpdateRequest request) {
		User user = userQueryService.me();
		Review review = reviewQueryService.getById(id);
		reviewCommandService.update(user, review, request.content(), request.rating());
	}

	@Transactional
	public void delete(Long id) {
		User user = userQueryService.me();
		Review review = reviewQueryService.getById(id);
		reviewCommandService.delete(user, review);
	}

	@Transactional(readOnly = true)
	public ReviewListResponse getReviews(Long targetId, ReviewType reviewType) {
		List<Review> reviews = reviewQueryService.getByTargetIdAndReviewType(targetId, reviewType);
		List<ReviewResponse> responses = reviews.stream()
			.map(ReviewResponse::from)
			.toList();

		return ReviewListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public ReviewListResponse getReviewsByTargetIds(List<Long> targetIds, ReviewType reviewType) {
		List<Review> reviews = reviewQueryService.getByTargetIdInAndReviewType(targetIds, reviewType);
		List<ReviewResponse> responses = reviews.stream()
			.map(ReviewResponse::from)
			.toList();

		return ReviewListResponse.of(responses);
	}
}
