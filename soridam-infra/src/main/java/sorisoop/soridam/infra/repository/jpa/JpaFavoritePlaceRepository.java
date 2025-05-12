package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlace;

public interface JpaFavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
	boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

	List<FavoritePlace> findByUserId(Long userId);

	void deleteByUserIdAndPlaceId(Long userId, Long placeId);

	List<FavoritePlace> findByPlaceId(Long placeId);
}
