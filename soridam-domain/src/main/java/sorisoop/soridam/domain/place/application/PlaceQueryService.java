package sorisoop.soridam.domain.place.application;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.place.domain.PlaceRepository;
import sorisoop.soridam.domain.place.domain.enums.Category;
import sorisoop.soridam.domain.place.exception.AddressNotFoundException;
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
			.orElseThrow(AddressNotFoundException::new);
	}

	public List<Place> getNearAddressesByPoint(double x, double y, int distanceMeter, List<Category> categories) {
		Point point = geometryUtils.createPoint(x, y);
		return placeRepository.findNearAddressesByPoint(point, distanceMeter, categories);
	}
}
