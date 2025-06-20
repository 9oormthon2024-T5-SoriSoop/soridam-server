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

	public List<Review> getByPlaceIdIn(List<Long> targetIds) {
		if (targetIds.isEmpty()) return List.of();
		return reviewRepository.findByPlaceIdIn(targetIds);
	}

	public List<Review> getByPlaceId(Long placeId) {
		return reviewRepository.findByPlaceId(placeId);
	}
}
