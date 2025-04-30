package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sorisoop.soridam.domain.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, Long> {
	List<Noise> findByUserId(Long userId);

	@Query("select n.id from Noise n where n.address.id = :addressId order by n.id desc")
	List<Long> findTop50IdByAddressId(@Param("addressId") Long addressId, Pageable pageable);
}
