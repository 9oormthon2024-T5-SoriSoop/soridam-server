package sorisoop.soridam.infra.repository.reward_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.reward_service.domain.PointRedemption;

public interface JpaPointRedemptionRepository extends JpaRepository<PointRedemption, Long> {
}
