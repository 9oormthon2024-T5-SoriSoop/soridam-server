package sorisoop.soridam.domain.reward.repository;

import java.util.Optional;

import sorisoop.soridam.domain.reward.domain.PointRedemption;

public interface PointRedemptionRepository {
	PointRedemption save(PointRedemption pointRedemption);

	Optional<PointRedemption> findById(Long id);
}
