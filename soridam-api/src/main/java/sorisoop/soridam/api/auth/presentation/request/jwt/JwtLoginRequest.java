package sorisoop.soridam.api.auth.presentation.request.jwt;

public record JwtLoginRequest(
	String email,
	String password
) {
}
