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
import sorisoop.soridam.domain.address.application.AddressCommandService;
import sorisoop.soridam.domain.address.application.AddressQueryService;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.enums.Category;
import sorisoop.soridam.infra.repository.redis.SummaryCacheService;

@Component
@RequiredArgsConstructor
public class AddressFacade {
	private final AddressQueryService addressQueryService;
	private final AddressCommandService addressCommandService;
	private final SummaryCacheService summaryCacheService;

	@Transactional
	public AddressPersistResponse create(AddressCreateRequest request) {
		Address address = addressCommandService.save(
			request.x(),
			request.y(),
			request.roadAddress(),
			request.regionAddress(),
			request.category(),
			request.placeName(),
			request.placeUrl()
		);
		return AddressPersistResponse.from(address);
	}

	@Transactional(readOnly = true)
	public AddressListResponse getAddressesNearPoint(double x, double y, int distanceMeter, List<Category> categories) {
		List<AddressResponse> responses = addressQueryService.getNearAddressesByPoint(x, y, distanceMeter, categories).stream()
			.map(AddressResponse::from)
			.toList();

		return AddressListResponse.of(responses);
	}

	@Transactional(readOnly = true)
	public AddressDetailResponse getById(Long id) {
		Address address = addressQueryService.getById(id);
		String summary = summaryCacheService.get(id);
		return AddressDetailResponse.from(address, summary);
	}

	@Transactional
	public AddressPersistResponse getOrCreate(AddressCreateRequest request) {
		Address address = addressQueryService.getByRoadAddressAndPlaceName(request.roadAddress(), request.placeName());

		if(address == null) {
			address = addressCommandService.save(
				request.x(),
				request.y(),
				request.roadAddress(),
				request.regionAddress(),
				request.category(),
				request.placeName(),
				request.placeUrl()
			);
		}

		return AddressPersistResponse.from(address);
	}
}
