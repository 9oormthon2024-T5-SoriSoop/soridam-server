package sorisoop.soridam.domain.user.user.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.domain.common.BaseTimeEntity;
import sorisoop.soridam.domain.common.Provider;
import sorisoop.soridam.domain.user.user.exception.InvalidPasswordException;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class User extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String oauthIdentity;

	@Column(unique = true)
	private String email;

	private String password;

	private String name;

	@Column(unique = true)
	private String nickname;

	@Enumerated(STRING)
	private Provider provider;

	@Enumerated(STRING)
	private Role role;

	private LocalDateTime lastLoginAt;

	private int totalPoint;

	public void isPasswordMatching(String rawPassword, PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(rawPassword, this.password)) {
			throw new InvalidPasswordException();
		}
	}

	public static User create(String email, String password, String name, String nickname) {
		return User.builder()
			.email(email)
			.password(password)
			.name(name)
			.nickname(nickname)
			.role(Role.USER)
			.totalPoint(0)
			.build();
	}

	public static User kakaoOidcCreate(String oauthIdentity, Provider provider, String name){
		return User.builder()
			.oauthIdentity(oauthIdentity)
			.name(name)
			.provider(provider)
			.role(Role.USER)
			.totalPoint(0)
			.build();
	}

	public static User googleOidcCreate(String oauthIdentity, Provider provider, String name, String email){
		return User.builder()
			.oauthIdentity(oauthIdentity)
			.name(name)
			.email(email)
			.provider(provider)
			.role(Role.USER)
			.totalPoint(0)
			.build();
	}

	public void updateLastLoginTime() {
		this.lastLoginAt = LocalDateTime.now();
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}

	public void subtractTotalPoint(int point) {
		this.totalPoint -= point;
	}
}
