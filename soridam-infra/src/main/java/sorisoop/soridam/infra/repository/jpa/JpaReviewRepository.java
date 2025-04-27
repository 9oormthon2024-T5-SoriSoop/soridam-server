package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.review.domain.Review;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByTargetIdIn(List<Long> targetIds);

	List<Review> findByTargetId(Long targetId);
}
