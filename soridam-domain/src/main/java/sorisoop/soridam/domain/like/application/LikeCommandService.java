package sorisoop.soridam.domain.like.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.like.domain.Like;
import sorisoop.soridam.domain.like.domain.LikeRepository;
import sorisoop.soridam.domain.like.domain.LikeType;
import sorisoop.soridam.domain.user.user.domain.User;

@Service
@RequiredArgsConstructor
public class LikeCommandService {
	private final LikeRepository likeRepository;

	public boolean toggleLike(User user, LikeType likeType, Long targetId) {
		return likeRepository.findByUserAndLikeTypeAndTargetId(user, likeType, targetId)
			.map(existingLike -> {
				likeRepository.delete(existingLike);
				return false;
			})
			.orElseGet(() -> {
				Like like = Like.create(user, likeType, targetId);
				likeRepository.save(like);
				return true;
			});
	}
}

