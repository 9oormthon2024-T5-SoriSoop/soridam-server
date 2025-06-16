package sorisoop.soridam.api.like.presentation.response;

import java.util.List;

import lombok.Builder;
import sorisoop.soridam.domain.like.application.dto.LikeInfoDto;

@Builder
public record LikeInfoListResponse(
	List<LikeInfoDto> responses
) {
	public static LikeInfoListResponse from(List<LikeInfoDto> responses) {
		return LikeInfoListResponse.builder()
			.responses(responses)
			.build();
	}
}
