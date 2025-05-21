package sorisoop.soridam.domain.reward_service.repository;

import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.reward_service.domain.Good;

@Repository
public interface GoodRepository {
	Good save(Good good);
}
