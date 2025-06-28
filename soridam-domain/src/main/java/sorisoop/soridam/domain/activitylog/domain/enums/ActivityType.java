package sorisoop.soridam.domain.activitylog.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActivityType {
	VIEW(1.0),
	LIKE(3.0),
	REVIEW(5.0),
	NOISE_REGISTER(4.0),
	VISIT(2.0),
	FAVORITE(4.0);

	private final Double score;
}
