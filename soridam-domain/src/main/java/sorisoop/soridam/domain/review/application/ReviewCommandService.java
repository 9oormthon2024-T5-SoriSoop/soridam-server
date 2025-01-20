package sorisoop.soridam.domain.review.application;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;
import sorisoop.soridam.domain.review.exception.ReviewNotFoundException;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.exception.InvalidUserException;
import sorisoop.soridam.globalutil.user.UserUtil;
import sorisoop.soridam.globalutil.uuid.UuidPrefix;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandService {
	private final ReviewRepository reviewRepository;

	public Review create(String targetId, UuidPrefix reviewType,
		String authorId, String content, BigDecimal rating) {
		Review review = Review.create(targetId, reviewType, authorId, content, rating);
		return reviewRepository.save(review);
	}

	public void update(Review review, String content, BigDecimal rating) {
		review.updateContent(content);
		review.updateRating(rating);
	}

	public void delete(User user, String id) {
		Review review = reviewRepository.findById(id)
			.orElseThrow(ReviewNotFoundException::new);

		validateUser(user.getId(), review.getAuthorId());

		reviewRepository.delete(review);
	}

	private void validateUser(String user1, String user2) {
		if (!UserUtil.isSameUser(user1, user2)) {
			throw new InvalidUserException();
		}
	}
}
