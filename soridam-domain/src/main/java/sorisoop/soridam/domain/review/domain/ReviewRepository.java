package sorisoop.soridam.domain.review.domain;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(Long id);

	void delete(Review review);

	List<Review> findByPlaceIdIn(List<Long> placeIds);

	List<Review> findByPlaceId(Long placeId);
}
