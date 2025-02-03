package sorisoop.soridam.infra;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.user.infrastructure.PasswordEncoder;

@Component
@RequiredArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoder {
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String encode(String rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}
}
