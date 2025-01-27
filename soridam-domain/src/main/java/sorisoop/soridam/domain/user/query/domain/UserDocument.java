package sorisoop.soridam.domain.user.query.domain;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import sorisoop.soridam.common.domain.Provider;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.common.domain.Role;

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

	private LocalDateTime deletedAt;

	@OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true, fetch = LAZY)
	@Builder.Default
	private List<Noise> noises = new ArrayList<>();
}
