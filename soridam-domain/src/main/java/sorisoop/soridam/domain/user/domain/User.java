package sorisoop.soridam.domain.user.domain;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;
import static sorisoop.soridam.infra.uuid.UuidPrefix.USER;

import java.time.LocalDate;
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
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.user.exception.InvalidPasswordException;
import sorisoop.soridam.infra.uuid.PrefixedUuid;

@Entity
@Getter
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class User extends BaseTimeEntity {
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
	private List<Noise> noises;

	public void isPasswordMatching(String rawPassword, PasswordEncoder passwordEncoder) {
		if (!passwordEncoder.matches(rawPassword, this.password)) {
			throw new InvalidPasswordException();
		}
	}
}
