package sorisoop.soridam.domain.like.application.dto;

import lombok.Builder;

@Builder
public record LikeInfoDto(
	long targetId,
	long likeCount,
	boolean liked
) {
	public static LikeInfoDto of(long targetId, long likeCount, boolean liked) {
		return LikeInfoDto.builder()
			.targetId(targetId)
			.likeCount(likeCount)
			.liked(liked)
			.build();
	}
}
