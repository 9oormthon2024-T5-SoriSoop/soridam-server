package sorisoop.soridam.domain.review.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
	private final JpaReviewRepository jpaReviewRepository;

	@Override
	public Review save(Review review) {
		return jpaReviewRepository.save(review);
	}

	@Override
	public Optional<Review> findById(String id) {
		return jpaReviewRepository.findById(id);
	}
}
