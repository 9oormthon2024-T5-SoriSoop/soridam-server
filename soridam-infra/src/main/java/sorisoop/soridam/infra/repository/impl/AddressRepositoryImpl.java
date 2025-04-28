package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.AddressRepository;
import sorisoop.soridam.domain.address.domain.enums.Category;
import sorisoop.soridam.infra.repository.jpa.JpaAddressRepository;
import sorisoop.soridam.infra.repository.jpa.QueryAddressRepository;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {
	private final JpaAddressRepository jpaAddressRepository;
	private final QueryAddressRepository queryAddressRepository;

	@Override
	public Address save(Address address) {
		return jpaAddressRepository.save(address);
	}

	@Override
	public Optional<Address> findById(Long id) {
		return jpaAddressRepository.findById(id);
	}

	@Override
	public List<Address> findAll() {
		return jpaAddressRepository.findAll();
	}

	@Override
	public void delete(Address address) {
		jpaAddressRepository.delete(address);
	}

	@Override
	public Optional<Address> findByRoadAddress(String roadAddress) {
		return jpaAddressRepository.findByRoadAddress(roadAddress);
	}

	@Override
	public List<Address> findNearAddressesByPoint(Point point, int distanceMeter, List<Category> categories) {
		return queryAddressRepository.findNearAddressesByPoint(point, distanceMeter, categories);
	}
}
