package sorisoop.soridam.infra.repository.impl;

import java.util.List;
import java.util.Optional;

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
	public long countByLikeTypeAndTargetId(LikeType type, long targetId) {
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

	@Override
	public void delete(Like like) {
		jpaLikeRepository.delete(like);
	}

	@Override
	public void save(Like like) {
		jpaLikeRepository.save(like);
	}

	@Override
	public Optional<Like> findByUserAndLikeTypeAndTargetId(User user, LikeType likeType, Long targetId) {
		return jpaLikeRepository.findByUserAndLikeTypeAndTargetId(user, likeType, targetId);
	}
}
