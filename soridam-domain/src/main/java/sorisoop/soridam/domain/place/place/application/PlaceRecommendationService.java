package sorisoop.soridam.domain.place.place.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.domain.PlaceEsQueryPort;
import sorisoop.soridam.domain.place.place.domain.PlaceRepository;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@RequiredArgsConstructor
public class PlaceRecommendationService {
	private final PlaceEsQueryPort placeEsQueryPort;
	private final PlaceRepository placeRepository;
	private final GeometryUtils geometryUtils;
}
