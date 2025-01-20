package sorisoop.soridam.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewType {
	NOISE("소음"),
	;

	private String description;
}
