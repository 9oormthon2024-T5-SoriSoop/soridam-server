package sorisoop.soridam.domain.favoriteplace.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePlaceRepository {
	FavoritePlace save(FavoritePlace favoritePlace);

	boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

	boolean existsById(Long id);

	List<FavoritePlace> findByUserId(Long userId);

	void deleteByUserIdAndPlaceId(Long userId, Long placeId);

	void deleteById(Long id);
}
