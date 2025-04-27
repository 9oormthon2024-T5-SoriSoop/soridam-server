package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

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
	public Optional<Review> findById(String id) {
		return jpaReviewRepository.findById(id);
	}

	@Override
	public void delete(Review review) {
		jpaReviewRepository.delete(review);
	}

	@Override
	public List<Review> findByTargetIdIn(List<Long> targetIds) {
		return jpaReviewRepository.findByTargetIdIn(targetIds);
	}

	@Override
	public List<Review> findByTargetId(Long targetId) {
		return jpaReviewRepository.findByTargetId(targetId);
	}
}
