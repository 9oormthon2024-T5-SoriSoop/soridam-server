package sorisoop.soridam.domain.favorite_service.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.favorite_service.domain.FavoritePlace;
import sorisoop.soridam.domain.favorite_service.domain.FavoritePlaceRepository;
import sorisoop.soridam.domain.favorite_service.exception.AlreadyExistFavoritePlaceException;
import sorisoop.soridam.domain.favorite_service.exception.FavoritePlaceNotFoundException;
import sorisoop.soridam.domain.place_service.place.domain.Place;
import sorisoop.soridam.domain.user_service.user.domain.User;

@Service
@RequiredArgsConstructor
public class FavoritePlaceCommandService {
	private final FavoritePlaceRepository favoritePlaceRepository;

	public FavoritePlace save(User user, Place place) {
		boolean alreadyExists = favoritePlaceRepository.existsByUserIdAndPlaceId(
			user.getId(), place.getId()
		);

		if (alreadyExists) {
			throw new AlreadyExistFavoritePlaceException();
		}
		FavoritePlace favoritePlace = FavoritePlace.create(user, place);
		return favoritePlaceRepository.save(favoritePlace);
	}

	public void deleteById(Long id) {
		boolean exists = favoritePlaceRepository.existsById(id);

		if (!exists) {
			throw new FavoritePlaceNotFoundException();
		}

		favoritePlaceRepository.deleteById(id);
	}

	public void deleteByUserIdAndPlaceId(Long userId, Long placeId) {
		boolean exists = favoritePlaceRepository.existsByUserIdAndPlaceId(userId, placeId);

		if (!exists) {
			throw new FavoritePlaceNotFoundException();
		}

		favoritePlaceRepository.deleteByUserIdAndPlaceId(userId, placeId);
	}
}
