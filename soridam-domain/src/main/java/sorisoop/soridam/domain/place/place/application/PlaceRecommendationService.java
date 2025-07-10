package sorisoop.soridam.domain.place.place.application;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.place.place.domain.PlaceEsQueryPort;

@Service
@RequiredArgsConstructor
public class PlaceRecommendationService {
	private final PlaceEsQueryPort placeEsQueryPort;

	public List<Long> recommendPlacesForUser(Long userId, double userCurrentLat, double userCurrentLon, int size) throws
		IOException {

		List<Long> recentPlaceId = placeEsQueryPort.getMyRecentPlaceIds(userId);
		if (recentPlaceId.isEmpty()) {
			return List.of();
		}

		List<Long> similarUserIds = placeEsQueryPort.getSimilarUserIds(recentPlaceId, userId);
		if (similarUserIds.isEmpty()) {
			return List.of();
		}

		Map<Long, Double> scoredPlaces = placeEsQueryPort.getRecommendedPlaces(
			similarUserIds,
			recentPlaceId,
			userCurrentLat,
			userCurrentLon
		);
		if (scoredPlaces.isEmpty()) {
			return List.of();
		}

		return scoredPlaces.entrySet().stream()
			.sorted(Map.Entry.<Long, Double>comparingByValue(Comparator.reverseOrder()))
			.limit(size)
			.map(Map.Entry::getKey)
			.toList();
	}
}
