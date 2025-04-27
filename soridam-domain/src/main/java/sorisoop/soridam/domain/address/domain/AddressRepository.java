package sorisoop.soridam.domain.address.domain;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
	Address save(Address address);

	Optional<Address> findById(Long id);

	List<Address> findAll();

	void delete(Address address);

	Optional<Address> findByRoadAddress(String roadAddress);
}
