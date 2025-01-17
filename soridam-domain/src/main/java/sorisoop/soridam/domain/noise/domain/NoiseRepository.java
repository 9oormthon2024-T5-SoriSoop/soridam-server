package sorisoop.soridam.domain.noise.domain;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;

public interface NoiseRepository {
	List<Noise> getNearbyNoises(Point point);

	List<Noise> findByAvgDecibelAndPoint(Point point, Radius radius, NoiseLevel noiseLevel);

	Optional<Noise> findById(String id);

	Noise save(Noise noise);

	void delete(Noise noise);
}
