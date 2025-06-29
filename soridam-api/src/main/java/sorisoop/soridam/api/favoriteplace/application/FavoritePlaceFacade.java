package sorisoop.soridam.api.favoriteplace.application;

import static sorisoop.soridam.domain.activitylog.domain.enums.ActivityType.FAVORITE;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.favoriteplace.presentation.request.FavoritePlaceCreateRequest;
import sorisoop.soridam.api.favoriteplace.presentation.response.FavoritePlacePersistResponse;
import sorisoop.soridam.api.favoriteplace.presentation.response.FavoritePlaceResponse;
import sorisoop.soridam.common.response.SliceResponse;
import sorisoop.soridam.domain.activitylog.application.ActivityLogService;
import sorisoop.soridam.domain.favorite.application.FavoritePlaceCommandService;
import sorisoop.soridam.domain.favorite.application.FavoritePlaceQueryService;
import sorisoop.soridam.domain.favorite.domain.FavoritePlace;
import sorisoop.soridam.domain.place.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.user.user.application.UserQueryService;
import sorisoop.soridam.domain.user.user.domain.User;

@Component
@RequiredArgsConstructor
public class FavoritePlaceFacade {
	private final UserQueryService userQueryService;
	private final PlaceQueryService placeQueryService;
	private final FavoritePlaceCommandService favoritePlaceCommandService;
	private final FavoritePlaceQueryService favoritePlaceQueryService;
	private final ActivityLogService activityLogService;

	@Transactional
	public FavoritePlacePersistResponse create(FavoritePlaceCreateRequest request) {
		User user = userQueryService.me();
		Place place = placeQueryService.getById(request.placeId());

		FavoritePlace favoritePlace = favoritePlaceCommandService.save(user, place);
		activityLogService.save(user, place, FAVORITE);
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
