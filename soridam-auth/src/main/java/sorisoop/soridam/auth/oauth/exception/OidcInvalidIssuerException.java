package sorisoop.soridam.auth.oauth.exception;

import static sorisoop.soridam.auth.oauth.exception.OidcExceptionCode.OIDC_INVALID_ISSUER;

import sorisoop.soridam.common.exception.CustomException;

public class OidcInvalidIssuerException extends CustomException {
	public OidcInvalidIssuerException() {
		super(OIDC_INVALID_ISSUER);
	}
}
