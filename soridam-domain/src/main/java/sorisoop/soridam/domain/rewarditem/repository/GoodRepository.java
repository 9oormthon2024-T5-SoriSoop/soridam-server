package sorisoop.soridam.domain.rewarditem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.rewarditem.domain.Good;

@Repository
public interface GoodRepository {
	Good save(Good good);

	List<Good> findByHiddenFalse();

	Optional<Good> findById(Long id);
}
