package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.infra.repository.jpa.JpaNoiseRepository;
import sorisoop.soridam.infra.repository.jpa.QueryNoiseRepository;

@Repository
@RequiredArgsConstructor
public class NoiseRepositoryImpl implements NoiseRepository {
	private final JpaNoiseRepository jpaNoiseRepository;
	private final QueryNoiseRepository queryNoiseRepository;

	@Override
	public Optional<Noise> findById(Long id) {
		return jpaNoiseRepository.findById(id);
	}

	@Override
	public Noise save(Noise noise) {
		return jpaNoiseRepository.save(noise);
	}

	@Override
	public void delete(Noise noise) {
		jpaNoiseRepository.delete(noise);
	}

	@Override
	public List<Noise> findByUserId(Long userId) {
		return jpaNoiseRepository.findByUserId(userId);
	}

	@Override
	public List<Noise> findByPlaceWithCursorAndAvgDecibelRange(
		Long placeId,
		String lastValue,
		int minAvg,
		int maxAvg,
		int limit,
		Sort sort
	) {
		return queryNoiseRepository.findByPlaceWithCursorAndAvgDecibelRange(
			placeId, lastValue, minAvg, maxAvg, limit, sort
		);
	}

	@Override
	public List<Long> findTop50IdByPlaceId(Long placeId, Pageable pageable) {
		return jpaNoiseRepository.findTop50IdByPlaceId(placeId, pageable);
	}

}
