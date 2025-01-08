package sorisoop.soridam.api.user.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryListResponse;
import sorisoop.soridam.api.noise.presentation.response.NoiseSummaryResponse;
import sorisoop.soridam.api.user.presentation.request.UserCreateRequest;
import sorisoop.soridam.api.user.presentation.response.UserPersistResponse;
import sorisoop.soridam.domain.noise.domain.Noise;
import sorisoop.soridam.domain.user.application.UserCommandService;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;

	public UserPersistResponse signUp(UserCreateRequest request){
		User user = userCommandService.signUp(
			request.email(),
			request.password(),
			request.name(),
			request.nickname(),
			request.birthDate(),
			request.phoneNumber(),
			request.profileImageUrl()
		);
		return UserPersistResponse.from(user);
	}

	@Transactional(readOnly = true)
	public NoiseSummaryListResponse getUserNoises(String id) {
		List<Noise> noises = userQueryService.getUserNoises(request, id);
		List<NoiseSummaryResponse> responses = noises.stream()
			.map(NoiseSummaryResponse::from)
			.toList();

		return NoiseSummaryListResponse.of(responses);
	}

	public User getById(String id) {
		return userQueryService.getById(id);
	}

	public User getByEmail(String email) {
		return userQueryService.getByEmail(email);
	}
}
