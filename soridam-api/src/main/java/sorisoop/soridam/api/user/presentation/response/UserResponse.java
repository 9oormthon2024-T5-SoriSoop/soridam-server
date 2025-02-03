package sorisoop.soridam.api.user.presentation.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import sorisoop.soridam.infra.persistence.mongo.document.UserDocument;

@Builder
public record UserResponse(
	String id,
	String email,
	String name,
	String nickname,
	LocalDate birthDate,
	String phoneNumber,
	String profileImageUrl,
	int point,
	LocalDateTime lastLoginAt,
	LocalDateTime createdAt
) {
	public static UserResponse from(UserDocument userDocument) {
		return new UserResponse(
			userDocument.getId(),
			userDocument.getEmail(),
			userDocument.getName(),
			userDocument.getNickname(),
			userDocument.getBirthDate(),
			userDocument.getPhoneNumber(),
			userDocument.getProfileImageUrl(),
			userDocument.getPoint(),
			userDocument.getLastLoginAt(),
			userDocument.getCreatedAt()
		);
	}
}

