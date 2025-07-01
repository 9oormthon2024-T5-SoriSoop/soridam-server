package sorisoop.soridam.domain.place.place.domain;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PlaceEsQueryPort {
	List<Long> getMyRecentPlaceIds(Long userId) throws IOException;
	List<Long> getSimilarUserIds(List<Long> placeIds, Long userId) throws IOException;
	Map<Long, Double> getRecommendedPlaces(List<Long> similarUserIds, List<Long> excludePlaceIds) throws IOException;
}

