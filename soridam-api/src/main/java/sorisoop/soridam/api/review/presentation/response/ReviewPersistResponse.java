package sorisoop.soridam.api.review.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import sorisoop.soridam.domain.review.domain.Review;

@Builder
public record ReviewPersistResponse(
	@Schema(description = "review ID", example = "asdfjklsadjklsamlsdfsldm", requiredMode = REQUIRED)
	String id
) {
	public static ReviewPersistResponse from(Review review){
		return ReviewPersistResponse.builder()
			.id(review.extractUuid())
			.build();
	}
}