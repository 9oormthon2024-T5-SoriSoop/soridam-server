package sorisoop.soridam.api.user.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.user.presentation.exception.UserNotFoundException;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final JpaUserRepository jpaUserRepository;

	@Transactional(readOnly = true)
	public NoiseSummaryListResponse getUserNoises(Long id) {
		User user = getById(id);

		List<Noise> noises = user.getNoises();

		List<NoiseSummaryResponse> responses = noises.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		return NoiseSummaryListResponse.of(responses);
	}

	public User getById(Long id) {
		return jpaUserRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
	}
}
