package sorisoop.soridam.auth.oauth.exception;

import static sorisoop.soridam.auth.oauth.exception.OidcExceptionCode.OIDC_INVALID_AUDIENCE;

import sorisoop.soridam.common.exception.CustomException;

public class OidcInvalidAudienceException extends CustomException {
	public OidcInvalidAudienceException() {
		super(OIDC_INVALID_AUDIENCE);
	}
}
