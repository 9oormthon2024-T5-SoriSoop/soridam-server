package sorisoop.soridam.domain.review.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;
import sorisoop.soridam.domain.review.exception.ReviewNotFoundException;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
	private final ReviewRepository reviewRepository;

	public Review getById(Long id) {
		return reviewRepository.findById(id)
			.orElseThrow(ReviewNotFoundException::new);
	}

	public List<Review> getByTargetIdIn(List<Long> targetIds) {
		return reviewRepository.findByTargetIdIn(targetIds);
	}

	public List<Review> getByTargetId(Long targetId) {
		return reviewRepository.findByTargetId(targetId);
	}
}
