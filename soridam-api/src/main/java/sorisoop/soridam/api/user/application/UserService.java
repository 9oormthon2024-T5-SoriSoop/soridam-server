package sorisoop.soridam.api.user.application;

import static sorisoop.soridam.infra.uuid.UuidPrefix.USER;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.user.presentation.exception.UserNotFoundException;
import sorisoop.soridam.api.user.presentation.request.UserCreateRequest;
import sorisoop.soridam.api.user.presentation.response.UserPersistResponse;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.user.domain.User;
import sorisoop.soridam.domain.user.infrastructure.JpaUserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	private final JpaUserRepository jpaUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public UserPersistResponse signUp(UserCreateRequest request){
		User user = User.create(
			request.email(),
			bCryptPasswordEncoder.encode(request.password()),
			request.name(),
			request.nickname(),
			request.birthDate(),
			request.phoneNumber(),
			request.profileImageUrl()
		);

		jpaUserRepository.save(user);
		return UserPersistResponse.from(user);
	}

	@Transactional(readOnly = true)
	public NoiseSummaryListResponse getUserNoises(String id) {
		User user = getById(id);

		List<Noise> noises = user.getNoises();

		List<NoiseSummaryResponse> responses = noises.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		return NoiseSummaryListResponse.of(responses);
	}

	public User getById(String id) {
		return jpaUserRepository.findById(USER + id)
			.orElseThrow(UserNotFoundException::new);
	}

	public User getByEmail(String email) {
		return jpaUserRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
	}
}
