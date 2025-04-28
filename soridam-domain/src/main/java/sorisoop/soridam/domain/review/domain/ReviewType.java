package sorisoop.soridam.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewType {
	ADDRESS("장소"),
	;

	private final String description;
}
