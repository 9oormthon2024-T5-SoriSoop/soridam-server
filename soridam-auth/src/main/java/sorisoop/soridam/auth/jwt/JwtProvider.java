package sorisoop.soridam.auth.jwt;

import static io.jsonwebtoken.Header.JWT_TYPE;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static javax.xml.crypto.dsig.SignatureProperties.TYPE;

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
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sorisoop.soridam.auth.jwt.exception.JwtInvalidException;
import sorisoop.soridam.auth.jwt.exception.JwtExpiredException;
import sorisoop.soridam.auth.jwt.exception.JwtMalformedException;
import sorisoop.soridam.auth.jwt.exception.JwtSignatureInvalidException;
import sorisoop.soridam.auth.jwt.exception.JwtUnsupportedException;

@Service
@Builder
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(String userId, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), userId);
    }

    private String makeToken(Date expiry, String userId) {
        Date now = new Date();
        return Jwts.builder()
            .setHeaderParam(TYPE, JWT_TYPE)
            .setIssuer(jwtProperties.getIssuer())
            .setIssuedAt(now)
            .setExpiration(expiry)
            .setSubject(userId)
            .claim("userId", userId)
            .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()), HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()))
                .build()
                .parseClaimsJws(token);
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
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()))
            .build().parseClaimsJws(token)
            .getBody();
    }
}
