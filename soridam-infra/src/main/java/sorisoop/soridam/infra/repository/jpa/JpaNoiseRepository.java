package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, Long> {
	List<Noise> findByUserId(String userId);

	List<Noise> findByAddressId(Long addressId);
}
