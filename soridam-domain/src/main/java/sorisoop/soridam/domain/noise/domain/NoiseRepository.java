package sorisoop.soridam.domain.noise.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface NoiseRepository {
	Optional<Noise> findById(Long id);

	Noise save(Noise noise);

	void delete(Noise noise);

	List<Noise> findByUserId(Long userId);

	List<Noise> findByAddressWithCursorAndAvgDecibelRange(Long addressId, String lastValue, int minAvg, int maxAvg, int limit, Sort sort);

	List<Long> findTop50IdByAddressId(Long id, Pageable pageable);
}
