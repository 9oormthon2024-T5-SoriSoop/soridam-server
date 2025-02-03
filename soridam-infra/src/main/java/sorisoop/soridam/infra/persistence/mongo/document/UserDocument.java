package sorisoop.soridam.infra.persistence.mongo.document;

import static jakarta.persistence.EnumType.STRING;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import sorisoop.soridam.domain.user.domain.Provider;
import sorisoop.soridam.domain.user.domain.Role;
import sorisoop.soridam.domain.user.domain.User;

@Getter
@Builder
@Document(collection = "users")
public class UserDocument {
	@Id
	private String id;

	private String oauthIdentity;

	@Column(unique = true)
	private String email;

	private String password;

	private String name;

	@Column(unique = true)
	private String nickname;

	private LocalDate birthDate;

	@Column(unique = true)
	private String phoneNumber;

	private String profileImageUrl;

	private int point;

	@Enumerated(STRING)
	private Provider provider;

	@Enumerated(STRING)
	private Role role;

	private LocalDateTime lastLoginAt;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public User toEntity() {
		return User.of(
			this.id,
			this.oauthIdentity,
			this.email,
			this.password,
			this.name,
			this.nickname,
			this.birthDate,
			this.phoneNumber,
			this.profileImageUrl,
			this.point,
			this.provider,
			this.role,
			this.lastLoginAt,
			this.createdAt,
			this.updatedAt
		);
	}
}
