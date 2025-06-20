package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;
import sorisoop.soridam.infra.repository.jpa.JpaReviewRepository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
	private final JpaReviewRepository jpaReviewRepository;

	@Override
	public Review save(Review review) {
		return jpaReviewRepository.save(review);
	}

	@Override
	public Optional<Review> findById(Long id) {
		return jpaReviewRepository.findById(id);
	}

	@Override
	public void delete(Review review) {
		jpaReviewRepository.delete(review);
	}

	@Override
	public List<Review> findByPlaceIdIn(Set<Long> placeIds) {
		return jpaReviewRepository.findByPlaceIdIn(placeIds);
	}

	@Override
	public List<Review> findByPlaceId(Long placeId) {
		return jpaReviewRepository.findByPlaceId(placeId);
	}
}
