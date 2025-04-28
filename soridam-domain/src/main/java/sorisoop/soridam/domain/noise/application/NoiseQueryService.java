package sorisoop.soridam.domain.noise.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.noise.exception.NoiseNotFoundException;

@Service
@RequiredArgsConstructor
public class NoiseQueryService {
	private final NoiseRepository noiseRepository;

	public List<Noise> getDetailNoise(Long addressId) {
		return noiseRepository.findByAddressId(addressId);
	}

	public Noise getById(Long id) {
		return noiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);
	}

	public List<Noise> getNoisesByUserId(Long userId) {
		return noiseRepository.findByUserId(userId);
	}
}
