package sorisoop.soridam.domain.favoriteplace.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePlaceRepository {
	boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

	List<FavoritePlace> findByUserId(Long userId);

	void deleteByUserIdAndPlaceId(Long userId, Long placeId);

	void deleteById(Long id);
}
