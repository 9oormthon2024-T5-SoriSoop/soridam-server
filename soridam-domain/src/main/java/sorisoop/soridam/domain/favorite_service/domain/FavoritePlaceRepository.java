package sorisoop.soridam.domain.favorite_service.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.notification_service.domain.dto.ReviewNotificationTarget;

@Repository
public interface FavoritePlaceRepository {
	FavoritePlace save(FavoritePlace favoritePlace);

	boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

	boolean existsById(Long id);

	List<FavoritePlace> findByUserIdWithCursor(Long userId, Long lastId, int limit);

	void deleteByUserIdAndPlaceId(Long userId, Long placeId);

	void deleteById(Long id);

	List<ReviewNotificationTarget> findTargetsByPlaceIdExcludingUser(Long placeId, Long userId);
}
