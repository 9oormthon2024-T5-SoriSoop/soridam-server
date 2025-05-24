package sorisoop.soridam.domain.place_service.noise.application;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place_service.noise.domain.Noise;
import sorisoop.soridam.domain.place_service.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.place_service.noise.exception.NoiseNotFoundException;

@Service
@RequiredArgsConstructor
public class NoiseQueryService {
	private final NoiseRepository noiseRepository;

	public List<Noise> getByPlaceWithCursorAndAvgDecibelRange(
		Long placeId,
		String lastValue,
		int minAvg,
		int maxAvg,
		int limit,
		Sort sort
	) {
		return noiseRepository.findByPlaceWithCursorAndAvgDecibelRange(
			placeId, lastValue, minAvg, maxAvg, limit, sort
		);
	}

	public Noise getById(Long id) {
		return noiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);
	}

	public List<Noise> getNoisesByUserId(Long userId) {
		return noiseRepository.findByUserId(userId);
	}
}
