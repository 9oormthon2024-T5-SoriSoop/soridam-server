package sorisoop.soridam.infra.repository.reward_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.reward_service.domain.UserPoint;

public interface JpaUserPointRepository extends JpaRepository<UserPoint, Long> {
}
