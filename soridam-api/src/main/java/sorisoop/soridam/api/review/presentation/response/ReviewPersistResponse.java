package sorisoop.soridam.api.review.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.review_service.domain.Review;

@Builder
public record ReviewPersistResponse(
	@Schema(description = "review ID", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static ReviewPersistResponse from(Review review){
		return ReviewPersistResponse.builder()
			.id(review.getId())
			.build();
	}
}