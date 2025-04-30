package sorisoop.soridam.domain.address.application;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.AddressRepository;
import sorisoop.soridam.domain.address.domain.enums.Category;
import sorisoop.soridam.domain.address.exception.AddressNotFoundException;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@RequiredArgsConstructor
public class AddressQueryService {
	private final AddressRepository addressRepository;
	private final GeometryUtils geometryUtils;

	public Address getByRoadAddressAndPlaceName(String roadAddress, String placeName) {
		return addressRepository.findByRoadAddressAndPlaceName(roadAddress, placeName)
			.orElse(null);
	}

	public Address getById(Long id) {
		return addressRepository.findById(id)
			.orElseThrow(AddressNotFoundException::new);
	}

	public List<Address> getNearAddressesByPoint(double x, double y, int distanceMeter, List<Category> categories) {
		Point point = geometryUtils.createPoint(x, y);
		return addressRepository.findNearAddressesByPoint(point, distanceMeter, categories);
	}
}
