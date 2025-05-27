package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sorisoop.soridam.domain.rewarditem.domain.Good;

public interface JpaGoodRepository extends JpaRepository<Good, Long> {
	List<Good> findByHiddenFalse();
}
