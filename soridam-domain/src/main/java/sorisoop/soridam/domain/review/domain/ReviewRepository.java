package sorisoop.soridam.domain.review.domain;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
	Review save(Review review);

	Optional<Review> findById(Long id);

	void delete(Review review);

	List<Review> findByTargetIdInAndReviewType(List<Long> targetIds, ReviewType reviewType);

	List<Review> findByTargetIdAndReviewType(Long targetId, ReviewType reviewType);
}
