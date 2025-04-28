package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.infra.repository.jpa.JpaNoiseRepository;

@Repository
@RequiredArgsConstructor
public class NoiseRepositoryImpl implements NoiseRepository {
	private final JpaNoiseRepository jpaNoiseRepository;

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
	public List<Noise> findByAddressId(Long addressId) {
		return jpaNoiseRepository.findByAddressId(addressId);
	}
}
