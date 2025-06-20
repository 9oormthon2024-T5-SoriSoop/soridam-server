package sorisoop.soridam.infra.repository.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.review.domain.Review;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByPlaceIdIn(Set<Long> placeIds);

	List<Review> findByPlaceId(Long targetId);
}
