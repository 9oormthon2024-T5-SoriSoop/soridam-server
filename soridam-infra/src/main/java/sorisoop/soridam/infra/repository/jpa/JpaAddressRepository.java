package sorisoop.soridam.infra.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.address.domain.Address;

public interface JpaAddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findByRoadAddressAndPlaceName(String roadAddress, String placeName);
}
