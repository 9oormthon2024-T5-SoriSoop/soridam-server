package sorisoop.soridam.domain.review.domain;

import java.util.Optional;

public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(String id);

	void delete(Review review);
}
