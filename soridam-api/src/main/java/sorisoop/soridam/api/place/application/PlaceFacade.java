package sorisoop.soridam.api.place.application;

import static sorisoop.soridam.domain.activitylog.domain.enums.ActivityType.VIEW;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.place.presentation.request.PlaceCreateRequest;
import sorisoop.soridam.api.place.presentation.response.PlaceDetailResponse;
import sorisoop.soridam.api.place.presentation.response.PlaceListResponse;
import sorisoop.soridam.api.place.presentation.response.PlacePersistResponse;
import sorisoop.soridam.api.place.presentation.response.PlaceResponse;
import sorisoop.soridam.domain.activitylog.application.ActivityLogService;
import sorisoop.soridam.domain.place.place.application.PlaceCommandService;
import sorisoop.soridam.domain.place.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.place.place.domain.enums.Category;
import sorisoop.soridam.domain.user.user.application.UserQueryService;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.infra.repository.redis.SummaryCacheService;

@Component
@RequiredArgsConstructor
public class PlaceFacade {
	private final PlaceQueryService placeQueryService;
	private final PlaceCommandService placeCommandService;
	private final SummaryCacheService summaryCacheService;
	private final UserQueryService userQueryService;
	private final ActivityLogService activityLogService;

	@Transactional
	public PlacePersistResponse create(PlaceCreateRequest request) {
		Place place = placeCommandService.save(
			request.x(),
			request.y(),
			request.roadAddress(),
			request.regionAddress(),
			request.category(),
			request.placeName(),
			request.placeUrl()
		);
		return PlacePersistResponse.from(place);
	}

	@Transactional(readOnly = true)
	public PlaceListResponse getNearPlacesByPoint(double x, double y, int distanceMeter, List<Category> categories) {
		List<PlaceResponse> responses = placeQueryService.getNearPlacesByPoint(x, y, distanceMeter, categories).stream()
			.map(PlaceResponse::from)
			.toList();

		return PlaceListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public PlaceDetailResponse getById(Long id) {
		Place place = placeQueryService.getById(id);
		String summary = summaryCacheService.get(id);

		User user = userQueryService.meForLog();
		if (user != null) {
			activityLogService.save(user, place, VIEW);
		}

		return PlaceDetailResponse.of(place, summary);
	}

	@Transactional
	public PlacePersistResponse getOrCreate(PlaceCreateRequest request) {
		Place place = placeQueryService.getByRoadAddressAndPlaceName(request.roadAddress(), request.placeName());

		if(place == null) {
			place = placeCommandService.save(
				request.x(),
				request.y(),
				request.roadAddress(),
				request.regionAddress(),
				request.category(),
				request.placeName(),
				request.placeUrl()
			);
		}

		return PlacePersistResponse.from(place);
	}

	@Transactional(readOnly = true)
	public PlaceListResponse getRecommendedPlaces(double latitude, double longitude) {
		User user = userQueryService.me();
		List<PlaceResponse> recommendedPlaces = placeQueryService.getRecommendedPlaces(latitude, longitude, user).stream()
			.map(PlaceResponse::from)
			.toList();
		return PlaceListResponse.of(recommendedPlaces);
	}
}
