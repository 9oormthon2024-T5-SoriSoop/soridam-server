package sorisoop.soridam.infra.repository.review_service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.review_service.domain.Review;
import sorisoop.soridam.domain.review_service.domain.ReviewRepository;
import sorisoop.soridam.domain.review_service.domain.ReviewType;
import sorisoop.soridam.infra.repository.review_service.jpa.JpaReviewRepository;

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
	public List<Review> findByTargetIdInAndReviewType(List<Long> targetIds, ReviewType reviewType) {
		return jpaReviewRepository.findByTargetIdInAndReviewType(targetIds, reviewType);
	}

	@Override
	public List<Review> findByTargetIdAndReviewType(Long targetId, ReviewType reviewType) {
		return jpaReviewRepository.findByTargetIdAndReviewType(targetId, reviewType);
	}
}
