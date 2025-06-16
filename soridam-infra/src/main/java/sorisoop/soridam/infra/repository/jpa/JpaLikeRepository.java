package sorisoop.soridam.infra.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sorisoop.soridam.domain.like.application.dto.LikeCountDto;
import sorisoop.soridam.domain.like.domain.Like;
import sorisoop.soridam.domain.like.domain.LikeType;
import sorisoop.soridam.domain.user.user.domain.User;

public interface JpaLikeRepository extends JpaRepository<Like, Long> {
	int countByLikeTypeAndTargetId(LikeType type, long targetId);

	List<Like> findByUserAndLikeTypeAndTargetIdIn(User user, LikeType likeType, List<Long> targetIds);

	@Query("SELECT new sorisoop.soridam.domain.like.application.dto.LikeCountDto(l.targetId, COUNT(l)) "
		+ "FROM Like l "
		+ "WHERE l.likeType = :likeType AND l.targetId IN :targetIds "
		+ "GROUP BY l.targetId")
	List<LikeCountDto> countByLikeTypeGroupByTargetId(LikeType likeType, List<Long> targetIds);

	boolean existsByUserAndLikeTypeAndTargetId(User user, LikeType likeType, long targetId);
}
