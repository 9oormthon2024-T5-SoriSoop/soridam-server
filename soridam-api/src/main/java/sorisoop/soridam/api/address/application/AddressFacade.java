package sorisoop.soridam.api.address.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.address.presentation.request.AddressCreateRequest;
import sorisoop.soridam.api.address.presentation.response.AddressDetailResponse;
import sorisoop.soridam.api.address.presentation.response.AddressListResponse;
import sorisoop.soridam.api.address.presentation.response.AddressPersistResponse;
import sorisoop.soridam.api.address.presentation.response.AddressResponse;
import sorisoop.soridam.domain.place.application.PlaceCommandService;
import sorisoop.soridam.domain.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place.domain.Place;
import sorisoop.soridam.domain.place.domain.enums.Category;
import sorisoop.soridam.infra.repository.redis.SummaryCacheService;

@Component
@RequiredArgsConstructor
public class AddressFacade {
	private final PlaceQueryService placeQueryService;
	private final PlaceCommandService placeCommandService;
	private final SummaryCacheService summaryCacheService;

	@Transactional
	public AddressPersistResponse create(AddressCreateRequest request) {
		Place place = placeCommandService.save(
			request.x(),
			request.y(),
			request.roadAddress(),
			request.regionAddress(),
			request.category(),
			request.placeName(),
			request.placeUrl()
		);
		return AddressPersistResponse.from(place);
	}

	@Transactional(readOnly = true)
	public AddressListResponse getAddressesNearPoint(double x, double y, int distanceMeter, List<Category> categories) {
		List<AddressResponse> responses = placeQueryService.getNearAddressesByPoint(x, y, distanceMeter, categories).stream()
			.map(AddressResponse::from)
			.toList();

		return AddressListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public AddressDetailResponse getById(Long id) {
		Place place = placeQueryService.getById(id);
		String summary = summaryCacheService.get(id);
		return AddressDetailResponse.of(place, summary);
	}

	@Transactional
	public AddressPersistResponse getOrCreate(AddressCreateRequest request) {
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

		return AddressPersistResponse.from(place);
	}
}
