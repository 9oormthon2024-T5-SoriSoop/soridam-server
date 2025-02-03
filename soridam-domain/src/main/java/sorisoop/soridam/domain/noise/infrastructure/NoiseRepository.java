package sorisoop.soridam.domain.noise.infrastructure;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;

import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.noise.domain.NoiseLevel;
import sorisoop.soridam.domain.noise.domain.Radius;

public interface NoiseRepository {
	List<Noise> getNearbyNoises(Point point);

	List<Noise> findByAvgDecibelAndPoint(Point point, Radius radius, NoiseLevel noiseLevel);

	Optional<Noise> findById(String id);

	Noise save(Noise noise);

	void delete(Noise noise);
}
