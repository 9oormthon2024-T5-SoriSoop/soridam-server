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

	public List<FavoritePlace> findByUserIdWithCursor(Long userId, Long lastId, int limit) {
		return favoritePlaceRepository.findByUserIdWithCursor(userId, lastId, limit);
	}
}
