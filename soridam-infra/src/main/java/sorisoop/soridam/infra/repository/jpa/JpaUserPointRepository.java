package sorisoop.soridam.infra.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.reward.domain.UserPoint;

public interface JpaUserPointRepository extends JpaRepository<UserPoint, Long> {
}
