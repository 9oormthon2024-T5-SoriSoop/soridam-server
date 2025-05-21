package sorisoop.soridam.infra.repository.reward_service.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward_service.repository.GoodRepository;
import sorisoop.soridam.infra.repository.reward_service.jpa.JpaGoodRepository;

@Repository
@RequiredArgsConstructor
public class GoodRepositoryImpl implements GoodRepository {
	private final JpaGoodRepository jpaGoodRepository;
}
