package sorisoop.soridam.domain.like.application;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.like.application.dto.LikeCountDto;
import sorisoop.soridam.domain.like.application.dto.LikeInfoDto;
import sorisoop.soridam.domain.like.domain.Like;
import sorisoop.soridam.domain.like.domain.LikeRepository;
import sorisoop.soridam.domain.like.domain.LikeType;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class LikeQueryService {
	private final LikeRepository likeRepository;

	public LikeInfoDto getLikeInfo(User user, LikeType likeType, long targetId) {
		long likeCount = likeRepository.countByLikeTypeAndTargetId(likeType, targetId);
		boolean liked = likeRepository.existsByUserAndLikeTypeAndTargetId(user, likeType, targetId);

		return LikeInfoDto.of(targetId, likeCount, liked);
	}


	public Map<Long, Boolean> getLikedMap(User user, LikeType likeType, List<Long> targetIds) {
		List<Like> likes = likeRepository.findByUserAndLikeTypeAndTargetIdIn(user, likeType, targetIds);
		Set<Long> likedIds = likes.stream()
			.map(Like::getTargetId)
			.collect(Collectors.toSet());

		return targetIds.stream()
			.collect(Collectors.toMap(
				id -> id,
				likedIds::contains
			));
	}

	public Map<Long, Long> getLikeCountMap(LikeType likeType, List<Long> targetIds) {
		List<LikeCountDto> results = likeRepository.countByLikeTypeGroupByTargetId(likeType, targetIds);
		return results.stream()
			.collect(Collectors.toMap(LikeCountDto::targetId, LikeCountDto::count));
	}
}
