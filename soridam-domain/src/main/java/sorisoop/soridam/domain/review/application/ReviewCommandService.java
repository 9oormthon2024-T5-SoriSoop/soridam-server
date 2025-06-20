package sorisoop.soridam.domain.review.application;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;
import sorisoop.soridam.domain.review.domain.ReviewTag;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.domain.user.user.exception.InvalidUserException;
import sorisoop.soridam.globalutil.user.UserUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandService {
	private final ReviewRepository reviewRepository;

	public Review create(Place place, Set<ReviewTag> tags,
		User author, String content, BigDecimal rating) {
		Review review = Review.create(place, tags, author, content, rating);
		return reviewRepository.save(review);
	}

	public void update(User user, Review review, String content, BigDecimal rating) {
		validateUser(user.getId(), review.getAuthor().getId());
		review.updateContent(content);
		review.updateRating(rating);
	}

	public void delete(User user, Review review) {
		validateUser(user.getId(), review.getAuthor().getId());
		reviewRepository.delete(review);
	}

	private void validateUser(Long user1, Long user2) {
		if (!UserUtil.isSameUser(user1, user2)) {
			throw new InvalidUserException();
		}
	}
}
