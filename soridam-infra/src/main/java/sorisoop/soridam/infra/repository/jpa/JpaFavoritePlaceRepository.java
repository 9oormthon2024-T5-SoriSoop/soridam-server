package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlace;

public interface JpaFavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
	boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

	List<FavoritePlace> findByUserId(Long userId);

	void deleteByUserIdAndPlaceId(Long userId, Long placeId);

	@Query("SELECT fp FROM FavoritePlace fp JOIN FETCH fp.user JOIN FETCH fp.place WHERE fp.place.id = :placeId")
	List<FavoritePlace> findByPlaceId(@Param("placeId") Long placeId);
}
