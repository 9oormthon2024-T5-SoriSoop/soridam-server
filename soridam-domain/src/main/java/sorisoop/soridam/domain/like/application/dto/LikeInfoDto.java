package sorisoop.soridam.domain.like.application.dto;

public record LikeInfoDto(
	long targetId,
	long likeCount,
	boolean liked
) {
	public static LikeInfoDto of(long targetId, long likeCount, boolean liked) {
		return new LikeInfoDto(targetId, likeCount, liked);
	}
}