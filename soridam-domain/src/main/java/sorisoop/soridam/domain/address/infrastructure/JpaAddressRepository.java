package sorisoop.soridam.domain.address.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.address.domain.Address;

public interface JpaAddressRepository extends JpaRepository<Address, String> {
}
