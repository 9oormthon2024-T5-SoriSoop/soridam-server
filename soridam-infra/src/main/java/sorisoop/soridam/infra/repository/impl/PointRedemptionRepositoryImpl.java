package sorisoop.soridam.infra.repository.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward.repository.PointRedemptionRepository;
import sorisoop.soridam.infra.repository.jpa.JpaPointRedemptionRepository;

@Repository
@RequiredArgsConstructor
public class PointRedemptionRepositoryImpl implements PointRedemptionRepository {
	private final JpaPointRedemptionRepository jpaPointRedemptionRepository;
}
