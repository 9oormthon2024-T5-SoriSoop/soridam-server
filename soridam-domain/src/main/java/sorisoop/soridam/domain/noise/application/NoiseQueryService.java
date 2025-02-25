package sorisoop.soridam.domain.noise.application;

import static sorisoop.soridam.globalutil.uuid.UuidPrefix.NOISE;

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

	public List<Noise> getDetailNoise(double x, double y) {
		Point point = geometryUtils.createPoint(x, y);
		return noiseRepository.findAllByAddress_Location(point);
	}

	public List<Noise> getNearbyNoise(double x, double y, Radius radius, NoiseLevel noiseLevel) {
		Point point = geometryUtils.createPoint(x, y);
		return noiseRepository.findByAvgDecibelAndPoint(point, radius, noiseLevel);
	}

	public Noise getNoise(String id) {
		return noiseRepository.findById(NOISE.getPrefix() + id)
			.orElseThrow(NoiseNotFoundException::new);
	}
}
