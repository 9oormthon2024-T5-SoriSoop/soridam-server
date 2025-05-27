package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.rewarditem.domain.Good;
import sorisoop.soridam.domain.rewarditem.repository.GoodRepository;
import sorisoop.soridam.infra.repository.jpa.JpaGoodRepository;

@Repository
@RequiredArgsConstructor
public class GoodRepositoryImpl implements GoodRepository {
	private final JpaGoodRepository jpaGoodRepository;

	@Override
	public Good save(Good good) {
		return jpaGoodRepository.save(good);
	}

	@Override
	public List<Good> findByHiddenFalse() {
		return jpaGoodRepository.findByHiddenFalse();
	}

	@Override
	public Optional<Good> findById(Long id) {
		return jpaGoodRepository.findById(id);
	}
}
