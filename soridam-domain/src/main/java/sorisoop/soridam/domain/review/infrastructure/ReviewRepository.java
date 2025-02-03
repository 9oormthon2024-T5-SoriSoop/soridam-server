package sorisoop.soridam.domain.review.infrastructure;

import java.util.Optional;

import sorisoop.soridam.domain.review.domain.Review;

public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(String id);

	void delete(Review review);
}
