package sorisoop.soridam.domain.like.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LikeType {
	REVIEW("리뷰"),
	PLACE("장소"),
	;

	private final String description;
}
