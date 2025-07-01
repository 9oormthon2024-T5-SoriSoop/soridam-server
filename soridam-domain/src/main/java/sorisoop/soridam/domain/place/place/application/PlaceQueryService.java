package sorisoop.soridam.domain.place.place.application;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.place.place.domain.PlaceRepository;
import sorisoop.soridam.domain.place.place.domain.enums.Category;
import sorisoop.soridam.domain.place.place.exception.PlaceNotFoundException;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@RequiredArgsConstructor
public class PlaceQueryService {
	private final PlaceRepository placeRepository;
	private final GeometryUtils geometryUtils;

	public Place getByRoadAddressAndPlaceName(String roadAddress, String placeName) {
		return placeRepository.findByRoadAddressAndPlaceName(roadAddress, placeName)
			.orElse(null);
	}

	public Place getById(Long id) {
		return placeRepository.findById(id)
			.orElseThrow(PlaceNotFoundException::new);
	}

	public List<Place> getNearPlacesByPoint(double x, double y, int distanceMeter, List<Category> categories) {
		Point point = geometryUtils.createPoint(x, y);
		return placeRepository.findNearPlacesByPoint(point, distanceMeter, categories);
	}

	public List<Place> getRecommendedPlaces(double x, double y, User user) {
		Point point = geometryUtils.createPoint(x, y);
		return List.of();
	}
}
