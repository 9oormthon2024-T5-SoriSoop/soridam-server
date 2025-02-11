package sorisoop.soridam.domain.review.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.review.domain.Review;

public interface JpaReviewRepository extends JpaRepository<Review, String> {
	List<Review> findByTargetIdIn(List<String> targetIds);
}
