package sorisoop.soridam.domain.noise.domain;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;

public interface NoiseRepository {
	Optional<Noise> findById(Long id);

	Noise save(Noise noise);

	void delete(Noise noise);

	List<Noise> findByUserId(Long userId);

	List<Noise> findByAddressId(Long addressId);
}
