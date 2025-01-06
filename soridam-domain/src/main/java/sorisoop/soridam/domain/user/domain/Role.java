package sorisoop.soridam.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	ADMIN("관리자"),
	PAID_USER("유료 사용자"),
	USER("일반 사용자"),
	;

	private final String description;
}
