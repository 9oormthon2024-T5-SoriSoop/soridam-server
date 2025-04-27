package sorisoop.soridam.domain.address.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.AddressRepository;
import sorisoop.soridam.domain.address.exception.AddressNotFoundException;

@Service
@RequiredArgsConstructor
public class AddressQueryService {
	private final AddressRepository addressRepository;

	public Address getByRoadAddress(String roadAddress) {
		return addressRepository.findByRoadAddress(roadAddress)
			.orElse(null);
	}

	public Address getById(Long id) {
		return addressRepository.findById(id)
			.orElseThrow(AddressNotFoundException::new);
	}
}
