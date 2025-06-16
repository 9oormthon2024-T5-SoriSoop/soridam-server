package sorisoop.soridam.infra.repository.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.like.domain.LikeRepository;
import sorisoop.soridam.infra.repository.jpa.JpaLikeRepository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {
	private final JpaLikeRepository jpaLikeRepository;
}
