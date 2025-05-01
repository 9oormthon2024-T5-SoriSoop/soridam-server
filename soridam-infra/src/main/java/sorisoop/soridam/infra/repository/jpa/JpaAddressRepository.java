package sorisoop.soridam.infra.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.place.domain.Place;

public interface JpaAddressRepository extends JpaRepository<Place, Long> {
	Optional<Place> findByRoadAddressAndPlaceName(String roadAddress, String placeName);
}
