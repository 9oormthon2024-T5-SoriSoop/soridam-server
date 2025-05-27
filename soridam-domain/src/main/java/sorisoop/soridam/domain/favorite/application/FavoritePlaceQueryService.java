package sorisoop.soridam.domain.favorite.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favorite.domain.FavoritePlace;
import sorisoop.soridam.domain.favorite.domain.FavoritePlaceRepository;
import sorisoop.soridam.domain.notification.domain.dto.ReviewNotificationTarget;

@Service
@RequiredArgsConstructor
public class FavoritePlaceQueryService {
	private final FavoritePlaceRepository favoritePlaceRepository;

	public List<FavoritePlace> findByUserIdWithCursor(Long userId, Long lastId, int limit) {
		return favoritePlaceRepository.findByUserIdWithCursor(userId, lastId, limit);
	}

	public List<ReviewNotificationTarget> findTargetsByPlaceIdExcludingUser(Long placeId, Long userId) {
		return favoritePlaceRepository.findTargetsByPlaceIdExcludingUser(placeId, userId);
	}
}
