package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sorisoop.soridam.domain.noise.domain.Noise;

public interface JpaNoiseRepository extends JpaRepository<Noise, Long> {
	List<Noise> findByUserId(Long userId);

	@Query("select n from Noise n where n.address.id = :addressId and (:lastId is null or n.id < :lastId) order by n.id desc")
	List<Noise> findByAddressIdWithCursor(Long addressId, Long lastId, Pageable pageable);
}
