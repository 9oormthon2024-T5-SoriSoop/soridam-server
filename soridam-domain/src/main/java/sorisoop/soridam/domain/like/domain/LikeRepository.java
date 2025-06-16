package sorisoop.soridam.domain.like.domain;

import java.util.List;

import sorisoop.soridam.domain.like.application.dto.LikeCountDto;
import sorisoop.soridam.domain.user.user.domain.User;

public interface LikeRepository {
	int countByLikeTypeAndTargetId(LikeType type, long targetId);

	List<Like> findByUserAndLikeTypeAndTargetIdIn(User user, LikeType likeType, List<Long> targetIds);

	List<LikeCountDto> countByLikeTypeGroupByTargetId(LikeType likeType, List<Long> targetIds);

	boolean existsByUserAndLikeTypeAndTargetId(User user, LikeType type, long targetId);
}
