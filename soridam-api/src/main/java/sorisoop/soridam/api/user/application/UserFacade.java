package sorisoop.soridam.api.user.application;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sorisoop.soridam.api.user.presentation.request.UserCreateRequest;
import sorisoop.soridam.api.user.presentation.response.UserPersistResponse;
import sorisoop.soridam.domain.user.command.application.UserCommandService;
import sorisoop.soridam.domain.user.command.domain.User;
import sorisoop.soridam.domain.user.query.application.UserQueryService;
import sorisoop.soridam.domain.user.query.domain.UserDocument;

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

	public UserDocument getUserDocument(String id) {
		return userQueryService.getUserInfo(id);
	}
}
