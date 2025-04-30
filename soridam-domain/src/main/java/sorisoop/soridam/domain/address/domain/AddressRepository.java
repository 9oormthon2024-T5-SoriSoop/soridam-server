package sorisoop.soridam.domain.address.domain;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;

import sorisoop.soridam.domain.address.domain.enums.Category;

public interface AddressRepository {
	Address save(Address address);

	Optional<Address> findById(Long id);

	List<Address> findAll();

	void delete(Address address);

	Optional<Address> findByRoadAddressAndPlaceName(String roadAddress, String placeName);

	List<Address> findNearAddressesByPoint(Point point, int distanceMeter, List<Category> categories);
}
