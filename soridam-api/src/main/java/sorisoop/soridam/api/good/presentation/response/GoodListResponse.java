package sorisoop.soridam.api.good.presentation.response;

import java.util.List;

import lombok.Builder;
import sorisoop.soridam.domain.rewarditem.domain.Good;

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
