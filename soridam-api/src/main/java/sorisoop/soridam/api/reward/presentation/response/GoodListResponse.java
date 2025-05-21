package sorisoop.soridam.api.reward.presentation.response;

import java.util.List;

import lombok.Builder;
import sorisoop.soridam.domain.reward_service.domain.Good;

@Builder
public record GoodListResponse(
	List<GoodResponse> response
) {
	public static GoodListResponse from(List<Good> response) {
		return GoodListResponse.builder()
			.response(response.stream()
				.map(GoodResponse::from)
				.toList())
			.build();
	}
}
