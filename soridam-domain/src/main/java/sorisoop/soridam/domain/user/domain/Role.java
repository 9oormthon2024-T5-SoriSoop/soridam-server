package sorisoop.soridam.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	ROLE_ADMIN("관리자"),
	ROLE_PAID_USER("유료 사용자"),
	ROLE_USER("일반 사용자"),
	ROLE_NOT_REGISTERED("미등록 사용자");

	private final String description;
}
