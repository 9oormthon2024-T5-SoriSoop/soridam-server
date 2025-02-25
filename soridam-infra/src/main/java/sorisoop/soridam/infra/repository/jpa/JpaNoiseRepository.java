package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, String> {
	List<Noise> findAllByAddress_Location(Point location);
}
