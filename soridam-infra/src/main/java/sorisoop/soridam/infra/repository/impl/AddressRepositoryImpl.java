package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.address.domain.Address;
import sorisoop.soridam.domain.address.domain.AddressRepository;
import sorisoop.soridam.infra.repository.jpa.JpaAddressRepository;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {
	private final JpaAddressRepository jpaAddressRepository;

	@Override
	public Address save(Address address) {
		return jpaAddressRepository.save(address);
	}

	@Override
	public Optional<Address> findById(String id) {
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
}
