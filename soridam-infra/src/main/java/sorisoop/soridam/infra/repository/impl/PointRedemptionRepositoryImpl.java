package sorisoop.soridam.infra.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward.domain.PointRedemption;
import sorisoop.soridam.domain.reward.repository.PointRedemptionRepository;
import sorisoop.soridam.infra.repository.jpa.JpaPointRedemptionRepository;

@Repository
@RequiredArgsConstructor
public class PointRedemptionRepositoryImpl implements PointRedemptionRepository {
	private final JpaPointRedemptionRepository jpaPointRedemptionRepository;

	@Override
	public PointRedemption save(PointRedemption pointRedemption) {
		return jpaPointRedemptionRepository.save(pointRedemption);
	}

	@Override
	public Optional<PointRedemption> findById(Long id) {
		return jpaPointRedemptionRepository.findById(id);
	}
}
