package sorisoop.soridam.domain.user.domain;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.common.uuid.UuidPrefix.USER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorisoop.soridam.common.domain.BaseTimeEntity;
import sorisoop.soridam.common.domain.UuidExtractable;
import sorisoop.soridam.common.uuid.PrefixedUuid;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.user.exception.InvalidPasswordException;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class User extends BaseTimeEntity implements UuidExtractable {
	@Id
	@PrefixedUuid(USER)
	private String id;

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

	@OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
	@Builder.Default
	private List<Noise> noises = new ArrayList<>();

	public void isPasswordMatching(String rawPassword, PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(rawPassword, this.password)) {
			throw new InvalidPasswordException();
		}
	}

	public static User create(String email, String password, String name, String nickname,
		LocalDate birthDate, String phoneNumber, String profileImageUrl) {
		return User.builder()
			.email(email)
			.password(password)
			.name(name)
			.nickname(nickname)
			.birthDate(birthDate)
			.phoneNumber(phoneNumber)
			.profileImageUrl(profileImageUrl)
			.point(0)
			.build();
	}
}
