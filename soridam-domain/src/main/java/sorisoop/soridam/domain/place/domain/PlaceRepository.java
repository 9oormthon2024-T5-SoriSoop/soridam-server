package sorisoop.soridam.domain.place.domain;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;

import sorisoop.soridam.domain.place.domain.enums.Category;

public interface PlaceRepository {
	Place save(Place place);

	Optional<Place> findById(Long id);

	List<Place> findAll();

	void delete(Place place);

	Optional<Place> findByRoadAddressAndPlaceName(String roadAddress, String placeName);

	List<Place> findNearPlacesByPoint(Point point, int distanceMeter, List<Category> categories);
}
