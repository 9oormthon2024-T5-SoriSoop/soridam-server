package sorisoop.soridam.infra.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.review.domain.Review;

public interface JpaReviewRepository extends JpaRepository<Review, String> {
}
