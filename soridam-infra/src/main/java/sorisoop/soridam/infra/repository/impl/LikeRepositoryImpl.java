package sorisoop.soridam.infra.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.like.application.dto.LikeCountDto;
import sorisoop.soridam.domain.like.domain.Like;
import sorisoop.soridam.domain.like.domain.LikeRepository;
import sorisoop.soridam.domain.like.domain.LikeType;
import sorisoop.soridam.domain.user.user.domain.User;
import sorisoop.soridam.infra.repository.jpa.JpaLikeRepository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {
	private final JpaLikeRepository jpaLikeRepository;

	@Override
	public int countByLikeTypeAndTargetId(LikeType type, long targetId) {
		return jpaLikeRepository.countByLikeTypeAndTargetId(type, targetId);
	}

	@Override
	public List<Like> findByUserAndLikeTypeAndTargetIdIn(User user, LikeType likeType, List<Long> targetIds) {
		return jpaLikeRepository.findByUserAndLikeTypeAndTargetIdIn(user, likeType, targetIds);
	}

	@Override
	public List<LikeCountDto> countByLikeTypeGroupByTargetId(LikeType likeType, List<Long> targetIds) {
		return jpaLikeRepository.countByLikeTypeGroupByTargetId(likeType, targetIds);
	}

	@Override
	public boolean existsByUserAndLikeTypeAndTargetId(User user, LikeType likeType, long targetId) {
		return jpaLikeRepository.existsByUserAndLikeTypeAndTargetId(user, likeType, targetId);
	}
}
