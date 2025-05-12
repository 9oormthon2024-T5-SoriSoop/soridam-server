package sorisoop.soridam.infra.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlace;
import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlaceRepository;
import sorisoop.soridam.infra.repository.jpa.JpaFavoritePlaceRepository;
import sorisoop.soridam.infra.repository.jpa.QueryFavoritePlaceRepository;

@Repository
@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoritePlaceRepository {
	private final JpaFavoritePlaceRepository jpaFavoritePlaceRepository;
	private final QueryFavoritePlaceRepository queryFavoritePlaceRepository;

	@Override
	public FavoritePlace save(FavoritePlace favoritePlace) {
		return jpaFavoritePlaceRepository.save(favoritePlace);
	}

	@Override
	public boolean existsByUserIdAndPlaceId(Long userId, Long placeId) {
		return jpaFavoritePlaceRepository.existsByUserIdAndPlaceId(userId, placeId);
	}

	@Override
	public boolean existsById(Long id) {
		return jpaFavoritePlaceRepository.existsById(id);
	}

	@Override
	public List<FavoritePlace> findByUserIdWithCursor(Long userId, Long lastId, int limit) {
		return queryFavoritePlaceRepository.findByUserIdWithCursor(userId, lastId, limit);
	}

	@Override
	public void deleteByUserIdAndPlaceId(Long userId, Long placeId) {
		jpaFavoritePlaceRepository.deleteByUserIdAndPlaceId(userId, placeId);
	}

	@Override
	public void deleteById(Long id) {
		jpaFavoritePlaceRepository.deleteById(id);
	}

	@Override
	public List<FavoritePlace> findByPlaceId(Long placeId) {
		return jpaFavoritePlaceRepository.findByPlaceId(placeId);
	}
}
