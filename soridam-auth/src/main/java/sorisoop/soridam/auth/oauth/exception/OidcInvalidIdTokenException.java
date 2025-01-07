package sorisoop.soridam.auth.oauth.exception;

import static sorisoop.soridam.auth.oauth.exception.OidcExceptionCode.OIDC_INVALID_ID_TOKEN;

import sorisoop.soridam.common.exception.CustomException;

public class OidcInvalidIdTokenException extends CustomException {
	public OidcInvalidIdTokenException() {
		super(OIDC_INVALID_ID_TOKEN);
	}
}
