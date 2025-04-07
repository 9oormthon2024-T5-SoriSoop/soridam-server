package sorisoop.soridam.domain.address.application;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.AddressRepository;
import sorisoop.soridam.domain.address.domain.enums.Category;
import sorisoop.soridam.globalutil.geometry.GeometryUtils;

@Service
@RequiredArgsConstructor
public class AddressCommandService {
	private final AddressRepository addressRepository;
	private final GeometryUtils geometryUtils;

	public Address save(double x, double y, String roadAddress, String regionAddress, Category category) {
		Point location = geometryUtils.createPoint(x, y);
		Address address = Address.create(location, roadAddress, regionAddress, category);
		return addressRepository.save(address);
	}
}
