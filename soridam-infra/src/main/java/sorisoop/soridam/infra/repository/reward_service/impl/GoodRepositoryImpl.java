package sorisoop.soridam.infra.repository.reward_service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward_service.domain.Good;
import sorisoop.soridam.domain.reward_service.repository.GoodRepository;
import sorisoop.soridam.infra.repository.reward_service.jpa.JpaGoodRepository;

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
