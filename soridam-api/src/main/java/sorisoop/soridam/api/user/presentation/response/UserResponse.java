package sorisoop.soridam.api.user.presentation.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import sorisoop.soridam.api.noise.presentation.response.NoiseResponse;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

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
	LocalDateTime createdAt,
	long noiseCount,
	long reviewCount
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
			userDocument.getCreatedAt(),
			userDocument.getNoises().size(),
			userDocument.getReviews().size()
		);
	}
}

