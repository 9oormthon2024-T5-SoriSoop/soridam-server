package sorisoop.soridam.infra.repository.reward_service.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward_service.repository.PointRedemptionRepository;
import sorisoop.soridam.infra.repository.reward_service.jpa.JpaPointRedemptionRepository;

@Repository
@RequiredArgsConstructor
public class PointRedemptionRepositoryImpl implements PointRedemptionRepository {
	private final JpaPointRedemptionRepository jpaPointRedemptionRepository;
}
