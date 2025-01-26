package sorisoop.soridam.domain.review.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;
import sorisoop.soridam.domain.review.exception.ReviewNotFoundException;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
	private final ReviewRepository reviewRepository;

	public Review getById(String id) {
		return reviewRepository.findById(id)
			.orElseThrow(ReviewNotFoundException::new);
	}
}
