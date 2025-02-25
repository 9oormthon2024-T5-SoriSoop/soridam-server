package sorisoop.soridam.api.user.application;

import static sorisoop.soridam.globalutil.uuid.UuidPrefix.USER;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.user.presentation.request.UserCreateRequest;
import sorisoop.soridam.api.user.presentation.response.UserInfoResponse;
import sorisoop.soridam.api.user.presentation.response.UserPersistResponse;
import sorisoop.soridam.domain.user.application.UserCommandService;
import sorisoop.soridam.domain.user.application.UserQueryService;
import sorisoop.soridam.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private static final String USER_PREFIX = USER.getPrefix();

	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;

	@Transactional
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
	public UserInfoResponse getById(String id) {
		User user = userQueryService.getById(USER_PREFIX + id);
		return UserInfoResponse.from(user);
	}
}
