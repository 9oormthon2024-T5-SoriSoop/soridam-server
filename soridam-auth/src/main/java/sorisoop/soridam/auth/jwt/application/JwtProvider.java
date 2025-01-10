package sorisoop.soridam.auth.jwt.application;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.jwt.JwtProperties;
import sorisoop.soridam.auth.jwt.exception.JwtExpiredException;
import sorisoop.soridam.auth.jwt.exception.JwtInvalidException;
import sorisoop.soridam.auth.jwt.exception.JwtMalformedException;
import sorisoop.soridam.auth.jwt.exception.JwtSignatureInvalidException;
import sorisoop.soridam.auth.jwt.exception.JwtUnsupportedException;
import sorisoop.soridam.domain.user.domain.Role;
import sorisoop.soridam.domain.user.exception.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    private final Header header = Jwts.header().type("JWT").build();

    public String generateAccessToken(String userId, Role role, Duration duration) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + duration.toMillis()), userId, role);
    }

    public String generateAccessToken(String userId, Role role) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + Duration.ofDays(2).toMillis()), userId, role);
    }

    public String generateRefreshToken(String userId, Role role) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + Duration.ofDays(7).toMillis()), userId, role);
    }

    private String makeToken(Date expiry, String userId, Role role) {
        Date now = new Date();

        return Jwts.builder()
            .header().add(header).and()
            .issuer(jwtProperties.getIssuer())
            .issuedAt(now)
            .expiration(expiry)
            .subject(userId)
            .claim("role", role.name())
            .signWith(jwtProperties.getSecretKey())
            .compact();
    }

    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getEncoded()))
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException();
        } catch (UnsupportedJwtException e) {
            throw new JwtUnsupportedException();
        } catch (MalformedJwtException e) {
            throw new JwtMalformedException();
        } catch (SignatureException e) {
            throw new JwtSignatureInvalidException();
        } catch (Exception e) {
            throw new JwtInvalidException();
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = getRoles(claims);

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        claims.getSubject(),
                        "",
                        authorities
                ), token, authorities
        );
    }

    public Set<SimpleGrantedAuthority> getRoles(Claims claims) {
        String role = claims.get("role", String.class);

		return switch (role) {
			case "ADMIN" -> Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
			case "PAID_USER" -> Collections.singleton(new SimpleGrantedAuthority("ROLE_PAID_USER"));
			case "USER" -> Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
			default -> throw new UnauthorizedException();
		};
    }

    public String getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getEncoded()))
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
