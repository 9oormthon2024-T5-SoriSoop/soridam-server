package sorisoop.soridam.api.review.application;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.review.presentation.request.ReviewCreateRequest;
import sorisoop.soridam.api.review.presentation.request.ReviewUpdateRequest;
import sorisoop.soridam.api.review.presentation.response.ReviewListResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewPersistResponse;
import sorisoop.soridam.api.review.presentation.response.ReviewResponse;
import sorisoop.soridam.domain.place.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.review.application.ReviewCommandService;
import sorisoop.soridam.domain.review.application.ReviewQueryService;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.user.user.application.UserQueryService;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.infra.notification.NotificationAsyncService;
import sorisoop.soridam.infra.notification.ReviewCreatedEvent;

@Component
@RequiredArgsConstructor
public class ReviewFacade {
	private final ReviewCommandService reviewCommandService;
	private final ReviewQueryService reviewQueryService;
	private final PlaceQueryService placeQueryService;
	private final UserQueryService userQueryService;
	private final NotificationAsyncService notificationAsyncService;

	@Transactional
	public ReviewPersistResponse create(ReviewCreateRequest request) {
		User author = userQueryService.me();
		Place place = placeQueryService.getById(request.placeId());
		Review review = reviewCommandService.create(
			place,
			request.tags(),
			author,
			request.content(),
			request.rating()
		);

		ReviewCreatedEvent event = ReviewCreatedEvent.from(review);
		notificationAsyncService.sendReviewNotification(event);
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
	public ReviewListResponse getReviews(Long placeId) {
		List<Review> reviews = reviewQueryService.getByPlaceId(placeId);
		List<ReviewResponse> responses = reviews.stream()
			.map(ReviewResponse::from)
			.toList();

		return ReviewListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public ReviewListResponse getReviewsByPlaceIds(Set<Long> placeIds) {
		List<Review> reviews = reviewQueryService.getByPlaceIdIn(placeIds);
		List<ReviewResponse> responses = reviews.stream()
			.map(ReviewResponse::from)
			.toList();

		return ReviewListResponse.of(responses);
	}
}
