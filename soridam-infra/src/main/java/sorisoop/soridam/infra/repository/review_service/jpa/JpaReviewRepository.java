package sorisoop.soridam.infra.repository.review_service.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.review_service.domain.Review;
import sorisoop.soridam.domain.review_service.domain.ReviewType;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByTargetIdInAndReviewType(List<Long> targetIds, ReviewType reviewType);

	List<Review> findByTargetIdAndReviewType(Long targetId, ReviewType reviewType);
}
