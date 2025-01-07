package sorisoop.soridam.auth.oauth.exception;

import static sorisoop.soridam.auth.oauth.exception.OidcExceptionCode.OIDC_EXPIRED;

import sorisoop.soridam.common.exception.CustomException;

public class OidcExpiredException extends CustomException {
	public OidcExpiredException() {
		super(OIDC_EXPIRED);
	}
}
