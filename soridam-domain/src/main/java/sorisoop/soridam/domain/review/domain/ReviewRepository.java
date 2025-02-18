package sorisoop.soridam.domain.review.domain;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(String id);

	void delete(Review review);

	List<Review> findByTargetIdIn(List<String> targetIds);

	List<Review> findByTargetId(String targetId);
}
