package sorisoop.soridam.api.user.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.user.presentation.request.UserCreateRequest;
import sorisoop.soridam.api.user.presentation.response.UserInfoResponse;
import sorisoop.soridam.api.user.presentation.response.UserPersistResponse;
import sorisoop.soridam.domain.user_service.user.application.UserCommandService;
import sorisoop.soridam.domain.user_service.user.application.UserQueryService;
import sorisoop.soridam.domain.user_service.user.domain.User;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;

	@Transactional
	public UserPersistResponse signUp(UserCreateRequest request){
		User user = userCommandService.signUp(
			request.email(),
			request.password(),
			request.name(),
			request.nickname()
		);
		return UserPersistResponse.from(user);
	}

	@Transactional(readOnly = true)
	public UserInfoResponse getById(Long id) {
		User user = userQueryService.getById(id);
		return UserInfoResponse.from(user);
	}
}
