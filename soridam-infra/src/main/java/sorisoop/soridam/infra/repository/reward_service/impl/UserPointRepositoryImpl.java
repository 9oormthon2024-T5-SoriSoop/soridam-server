package sorisoop.soridam.infra.repository.reward_service.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward_service.repository.UserPointRepository;
import sorisoop.soridam.infra.repository.reward_service.jpa.JpaUserPointRepository;

@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {
	private final JpaUserPointRepository jpaUserPointRepository;
}
