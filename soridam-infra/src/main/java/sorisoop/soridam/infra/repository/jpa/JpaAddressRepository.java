package sorisoop.soridam.infra.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.address.domain.Address;

public interface JpaAddressRepository extends JpaRepository<Address, String> {
}
