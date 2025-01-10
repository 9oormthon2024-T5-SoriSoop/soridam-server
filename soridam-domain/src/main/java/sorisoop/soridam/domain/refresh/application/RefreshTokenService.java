package sorisoop.soridam.domain.refresh.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.domain.refresh.RefreshToken;
import sorisoop.soridam.domain.refresh.RefreshTokenRepository;
import sorisoop.soridam.domain.refresh.exception.RefreshTokenNotFoundException;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshToken getToken(String token) {
		return refreshTokenRepository.findByRefreshToken(token)
			.orElseThrow(RefreshTokenNotFoundException::new);
	}

	public void save(String userId, String token) {
		refreshTokenRepository.findById(userId)
			.ifPresent(refreshTokenRepository::delete);
		refreshTokenRepository.save(RefreshToken.of(userId, token));
	}}
