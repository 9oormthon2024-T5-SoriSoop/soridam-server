package sorisoop.soridam.domain.review.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(Long id);

	void delete(Review review);

	List<Review> findByPlaceIdIn(Set<Long> placeIds);

	List<Review> findByPlaceId(Long placeId);
}
