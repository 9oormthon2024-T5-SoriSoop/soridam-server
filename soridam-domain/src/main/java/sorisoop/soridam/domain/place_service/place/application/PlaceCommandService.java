package sorisoop.soridam.domain.place_service.place.application;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place_service.place.domain.Place;
import sorisoop.soridam.domain.place_service.place.domain.PlaceRepository;
import sorisoop.soridam.domain.place_service.place.domain.enums.Category;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@RequiredArgsConstructor
public class PlaceCommandService {
	private final PlaceRepository placeRepository;
	private final GeometryUtils geometryUtils;

	public Place save(double x, double y, String roadAddress, String regionAddress, Category category, String placeName, String placeUrl) {
		Point location = geometryUtils.createPoint(x, y);
		Place place = Place.create(location, roadAddress, regionAddress, category, placeName, placeUrl);
		return placeRepository.save(place);
	}

	public void saveIfNotExists(double x, double y, String roadAddress, String regionAddress, Category category, String placeName, String placeUrl) {
		Point location = geometryUtils.createPoint(x, y);

		boolean exists = placeRepository.existsByRoadAddressAndPlaceName(roadAddress, placeName);
		if (!exists) {
			Place place = Place.create(location, roadAddress, regionAddress, category, placeName, placeUrl);
			placeRepository.save(place);
		}
	}

}
