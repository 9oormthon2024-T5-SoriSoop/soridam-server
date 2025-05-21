package sorisoop.soridam.infra.repository.place_service.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.place_service.place.domain.Place;

public interface JpaPlaceRepository extends JpaRepository<Place, Long> {
	Optional<Place> findByRoadAddressAndPlaceName(String roadAddress, String placeName);

	boolean existsByRoadAddressAndPlaceName(String roadAddress, String placeName);
}
