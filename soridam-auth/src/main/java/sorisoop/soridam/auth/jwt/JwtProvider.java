package sorisoop.soridam.auth.jwt;

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
import sorisoop.soridam.auth.jwt.exception.JwtExpiredException;
import sorisoop.soridam.auth.jwt.exception.JwtInvalidException;
import sorisoop.soridam.auth.jwt.exception.JwtMalformedException;
import sorisoop.soridam.auth.jwt.exception.JwtSignatureInvalidException;
import sorisoop.soridam.auth.jwt.exception.JwtUnsupportedException;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    private final Header header = Jwts.header().type("JWT").build();

    public String generateToken(String userId, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), userId);
    }

    private String makeToken(Date expiry, String userId) {
        Date now = new Date();

        return Jwts.builder()
            .header().add(header).and()
            .issuer(jwtProperties.getIssuer())
            .issuedAt(now)
            .expiration(expiry)
            .subject(userId)
            .claim("userId", userId)
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
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_USER")
        );
        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        claims.getSubject(),
                        "",
                        authorities
                ), token, authorities
        );
    }

    public String getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("userId", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getEncoded()))
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
