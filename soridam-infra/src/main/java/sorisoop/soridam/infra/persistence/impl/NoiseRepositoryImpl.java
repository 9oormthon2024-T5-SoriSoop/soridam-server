package sorisoop.soridam.infra.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.infrastructure.NoiseRepository;
import sorisoop.soridam.domain.noise.domain.Radius;
import sorisoop.soridam.infra.persistence.jpa.JpaNoiseRepository;

@Repository
@RequiredArgsConstructor
public class NoiseRepositoryImpl implements NoiseRepository {
	private final JpaNoiseRepository jpaNoiseRepository;
	// private final QueryNoiseRepository queryNoiseRepository;

	@Override
	public List<Noise> getNearbyNoises(Point point) {
		// return queryNoiseRepository.getNearbyNoises(point);
		return List.of();
	}

	@Override
	public List<Noise> findByAvgDecibelAndPoint(Point point, Radius radius, NoiseLevel noiseLevel) {
		// return queryNoiseRepository.findByAvgDecibelAndPoint(point, radius, noiseLevel);
		return List.of();
	}

	@Override
	public Optional<Noise> findById(String id) {
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
}
