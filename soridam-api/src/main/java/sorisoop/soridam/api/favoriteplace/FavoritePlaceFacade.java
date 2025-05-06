package sorisoop.soridam.api.favoriteplace;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.favoriteplace.presentation.request.FavoritePlaceCreateRequest;
import sorisoop.soridam.api.favoriteplace.presentation.response.FavoritePlacePersistResponse;
import sorisoop.soridam.api.favoriteplace.presentation.response.FavoritePlaceResponse;
import sorisoop.soridam.common.response.SliceResponse;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceCommandService;
import sorisoop.soridam.domain.favoriteplace.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.favoriteplace.domain.FavoritePlace;
import sorisoop.soridam.domain.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class FavoritePlaceFacade {
	private final UserQueryService userQueryService;
	private final PlaceQueryService placeQueryService;
	private final FavoritePlaceCommandService favoritePlaceCommandService;
	private final FavoritePlaceQueryService favoritePlaceQueryService;

	@Transactional
	public FavoritePlacePersistResponse create(FavoritePlaceCreateRequest request) {
		User user = userQueryService.me();
		Place place = placeQueryService.getById(request.placeId());

		FavoritePlace favoritePlace = favoritePlaceCommandService.save(user, place);

		return FavoritePlacePersistResponse.from(favoritePlace);
	}

	@Transactional
	public void deleteByUserIdAndPlaceId(Long placeId) {
		User user = userQueryService.me();
		favoritePlaceCommandService.deleteByUserIdAndPlaceId(user.getId(), placeId);
	}

	@Transactional(readOnly = true)
	public SliceResponse<FavoritePlaceResponse> findByUserId(Long lastId, int limit) {
		User user = userQueryService.me();
		List<FavoritePlace> favoritePlaces = favoritePlaceQueryService.findByUserIdWithCursor(
			user.getId(), lastId, limit + 1
		);

		boolean hasNext = favoritePlaces.size() > limit;
		if (hasNext) favoritePlaces = favoritePlaces.subList(0, limit);

		List<FavoritePlaceResponse> responses = favoritePlaces.stream()
			.map(FavoritePlaceResponse::from)
			.toList();

		String newLastCursor = responses.isEmpty() ? null : String.valueOf(responses.get(responses.size() - 1).id());

		return SliceResponse.of(responses, newLastCursor, hasNext);
	}
}
