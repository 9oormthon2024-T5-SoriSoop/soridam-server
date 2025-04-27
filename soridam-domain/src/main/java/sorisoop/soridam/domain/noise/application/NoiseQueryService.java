package sorisoop.soridam.domain.noise.application;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.NoiseRepository;
import sorisoop.soridam.domain.noise.domain.Radius;
import sorisoop.soridam.domain.noise.exception.NoiseNotFoundException;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@RequiredArgsConstructor
public class NoiseQueryService {
	private final NoiseRepository noiseRepository;
	private final GeometryUtils geometryUtils;

	public List<Noise> getDetailNoise(Long addressId) {
		return noiseRepository.findByAddressId(addressId);
	}

	public List<Noise> getNearbyNoise(double x, double y, Radius radius, NoiseLevel noiseLevel) {
		Point point = geometryUtils.createPoint(x, y);
		return noiseRepository.findByAvgDecibelAndPoint(point, radius, noiseLevel);
	}

	public Noise getById(Long id) {
		return noiseRepository.findById(id)
			.orElseThrow(NoiseNotFoundException::new);
	}

	public List<Noise> getNoisesByUserId(String userId) {
		return noiseRepository.findByUserId(userId);
	}
}
