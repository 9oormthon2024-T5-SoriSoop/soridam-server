package sorisoop.soridam.infra.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.reward.domain.PointRedemption;

public interface JpaPointRedemptionRepository extends JpaRepository<PointRedemption, Long> {
}
