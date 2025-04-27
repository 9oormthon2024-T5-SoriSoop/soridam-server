package sorisoop.soridam.domain.refresh.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.refresh.domain.RefreshToken;
import sorisoop.soridam.domain.refresh.domain.RefreshTokenRepository;
import sorisoop.soridam.domain.refresh.exception.RefreshTokenNotFoundException;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshToken getToken(String token) {
		return refreshTokenRepository.findByRefreshToken(token)
			.orElseThrow(RefreshTokenNotFoundException::new);
	}

	public void save(Long id, String token) {
		refreshTokenRepository.save(RefreshToken.of(id, token));
	}
}
