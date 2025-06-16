package sorisoop.soridam.domain.like.application.dto;

public record LikeInfoDto(
	long targetId,
	int likeCount,
	boolean liked
) {}
