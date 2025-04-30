package sorisoop.soridam.api.scheduler;

import static sorisoop.soridam.domain.review.domain.ReviewType.ADDRESS;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.AddressRepository;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.review.domain.Review;
import sorisoop.soridam.domain.review.domain.ReviewRepository;
import sorisoop.soridam.infra.openai.OpenAiService;
import sorisoop.soridam.infra.repository.redis.SummaryCacheService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressSummaryScheduler {
	private final AddressRepository addressRepository;
	private final ReviewRepository reviewRepository;
	private final NoiseRepository noiseRepository;
	private final OpenAiService openaiService;
	private final SummaryCacheService summaryCacheService;

	@Scheduled(cron = "0 0 3 * * MON")
	public void summarizeAllAddresses() {
		log.info("장소 요약 스케줄러 시작");

		List<Address> addresses = addressRepository.findAll();

		for (Address address : addresses) {
			try {
				List<Long> noiseIds = noiseRepository.findTop50IdByAddressId(address.getId(), PageRequest.of(0, 50));
				if (noiseIds.size() < 50) continue;

				List<Review> reviews = reviewRepository.findByTargetIdInAndReviewType(noiseIds, ADDRESS);

				List<String> contents = reviews.stream()
					.map(Review::getContent)
					.collect(Collectors.toList());

				String summary = openaiService.summarizeReviews(address, contents);

				summaryCacheService.save(address.getId(), summary);

				log.info("요약 성공 - addressId={} summary={}", address.getId(), summary);
			} catch (Exception e) {
				log.warn("요약 실패 - addressId={}, error={}", address.getId(), e.getMessage());
			}
		}

		log.info("장소 요약 스케줄러 완료");
	}

}
