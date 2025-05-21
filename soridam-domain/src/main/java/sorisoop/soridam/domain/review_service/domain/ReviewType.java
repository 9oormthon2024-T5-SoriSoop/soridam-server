package sorisoop.soridam.domain.review_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewType {
	ADDRESS("장소"),
	;

	private final String description;
}
