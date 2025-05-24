package sorisoop.soridam.infra.repository.favorite_service.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sorisoop.soridam.domain.favorite_service.domain.FavoritePlace;
import sorisoop.soridam.domain.notification_service.domain.dto.ReviewNotificationTarget;

public interface JpaFavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
	boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

	List<FavoritePlace> findByUserId(Long userId);

	void deleteByUserIdAndPlaceId(Long userId, Long placeId);

	@Query("""
    	SELECT new sorisoop.soridam.domain.notification_service.domain.dto.ReviewNotificationTarget(
        	fp.user.id,
        	fp.place.id,
        	fp.place.placeName
    	)
    	FROM FavoritePlace fp
    	WHERE fp.place.id = :placeId
      	AND fp.user.id <> :excludeUserId
	""")
	List<ReviewNotificationTarget> findTargetsByPlaceIdExcludingUser(
		@Param("placeId") Long placeId,
		@Param("excludeUserId") Long excludeUserId
	);
}
