package sorisoop.soridam.infra.repository.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.reward.repository.UserPointRepository;
import sorisoop.soridam.infra.repository.jpa.JpaUserPointRepository;

@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {
	private final JpaUserPointRepository jpaUserPointRepository;
}
