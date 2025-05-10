package sorisoop.soridam.infra.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.place.domain.Place;

public interface JpaPlaceRepository extends JpaRepository<Place, Long> {
	Optional<Place> findByRoadAddressAndPlaceName(String roadAddress, String placeName);

	boolean existsByRoadAddressAndPlaceName(String roadAddress, String placeName);
}
