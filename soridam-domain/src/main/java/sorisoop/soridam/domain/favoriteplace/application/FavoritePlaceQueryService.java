package sorisoop.soridam.domain.favoriteplace.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlace;
import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlaceRepository;

@Service
@RequiredArgsConstructor
public class FavoritePlaceQueryService {
	private final FavoritePlaceRepository favoritePlaceRepository;

	public boolean existsUserIdAndPlaceId(Long userId, Long placeId) {
		return favoritePlaceRepository.existsByUserIdAndPlaceId(userId, placeId);
	}

	public List<FavoritePlace> findByUserId(Long userId) {
		return favoritePlaceRepository.findByUserId(userId);
	}
}
