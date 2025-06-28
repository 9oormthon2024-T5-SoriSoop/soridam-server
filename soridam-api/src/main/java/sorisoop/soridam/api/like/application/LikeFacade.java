package sorisoop.soridam.api.like.application;

import static sorisoop.soridam.domain.like.domain.LikeType.PLACE;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.like.presentation.response.LikeInfoListResponse;
import sorisoop.soridam.domain.activitylog.application.ActivityLogService;
import sorisoop.soridam.domain.activitylog.domain.enums.ActivityType;
import sorisoop.soridam.domain.like.application.LikeCommandService;
import sorisoop.soridam.domain.like.application.LikeQueryService;
import sorisoop.soridam.domain.like.application.dto.LikeInfoDto;
import sorisoop.soridam.domain.like.domain.LikeType;
import sorisoop.soridam.domain.place.place.application.PlaceQueryService;
import sorisoop.soridam.domain.place.place.domain.Place;
import sorisoop.soridam.domain.user.user.application.UserQueryService;
import sorisoop.soridam.domain.user.user.domain.User;

@Component
@RequiredArgsConstructor
public class LikeFacade {
	private final LikeCommandService likeCommandService;
	private final LikeQueryService likeQueryService;
	private final UserQueryService userQueryService;
	private final PlaceQueryService placeQueryService;
	private final ActivityLogService activityLogService;

	@Transactional
	public boolean toggleLike(LikeType likeType, long targetId) {
		User user = userQueryService.me();
		boolean result = likeCommandService.toggleLike(user, likeType, targetId);
		if (result && likeType == PLACE) {
			Place place = placeQueryService.getById(targetId);
			activityLogService.save(user, place, ActivityType.LIKE);
		}
		return result;
	}

	@Transactional(readOnly = true)
	public LikeInfoDto getLikeInfo(LikeType likeType, long targetId) {
		User user = userQueryService.me();
		return likeQueryService.getLikeInfo(user, likeType, targetId);
	}

	@Transactional(readOnly = true)
	public LikeInfoListResponse getLikedMap(LikeType likeType, List<Long> targetIds) {
		User user = userQueryService.me();
		Map<Long, Boolean> likedMap = likeQueryService.getLikedMap(user, likeType, targetIds);
		Map<Long, Long> likeCountMap = likeQueryService.getLikeCountMap(likeType, targetIds);

		List<LikeInfoDto> results = targetIds.stream()
			.map(id -> LikeInfoDto.of(id, likeCountMap.getOrDefault(id, 0L), likedMap.getOrDefault(id, false)))
			.toList();

		return LikeInfoListResponse.from(results);
	}
}
