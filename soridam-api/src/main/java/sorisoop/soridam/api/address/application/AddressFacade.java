package sorisoop.soridam.api.address.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.address.presentation.request.AddressCreateRequest;
import sorisoop.soridam.api.address.presentation.response.AddressPersistResponse;
import sorisoop.soridam.api.address.presentation.response.AddressResponse;
import sorisoop.soridam.domain.address.application.AddressCommandService;
import sorisoop.soridam.domain.address.application.AddressQueryService;
import sorisoop.soridam.domain.address.domain.Address;

@Component
@RequiredArgsConstructor
public class AddressFacade {
	private final AddressQueryService addressQueryService;
	private final AddressCommandService addressCommandService;

	@Transactional
	public AddressPersistResponse create(AddressCreateRequest request) {
		Address address = addressCommandService.save(
			request.x(),
			request.y(),
			request.roadAddress(),
			request.regionAddress(),
			request.category()
		);
		return AddressPersistResponse.from(address);
	}

	@Transactional(readOnly = true)
	public AddressResponse getByRoadAddress(String roadAddress) {
		Address address = addressQueryService.getByRoadAddress(roadAddress);
		return AddressResponse.from(address);
	}

	@Transactional(readOnly = true)
	public AddressResponse getById(Long id) {
		Address address = addressQueryService.getById(id);
		return AddressResponse.from(address);
	}

	@Transactional
	public AddressPersistResponse getOrCreate(AddressCreateRequest request) {
		Address address = addressQueryService.getByRoadAddress(request.roadAddress());

		if(address == null) {
			address = addressCommandService.save(
				request.x(),
				request.y(),
				request.roadAddress(),
				request.regionAddress(),
				request.category()
			);
		}

		return AddressPersistResponse.from(address);
	}
}
