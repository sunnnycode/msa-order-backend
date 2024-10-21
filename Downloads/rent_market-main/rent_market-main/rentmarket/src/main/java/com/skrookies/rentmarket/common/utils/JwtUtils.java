package com.skrookies.rentmarket.common.utils;

import com.skrookies.rentmarket.domain.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static java.lang.System.getenv;

@Slf4j
@Component
public class JwtUtils {
    private Key hmacKey;
    private Long expirationTime;
    private Map<String,String> env;

    public JwtUtils() {
        this.env = getenv();
        this.hmacKey = hmacKey = new SecretKeySpec(
                Base64.getDecoder().decode(env.get("JWT_SECRET_KEY")), SignatureAlgorithm.HS256.getJcaName()
        );
        this.expirationTime = Long.parseLong(env.get("JWT_TIME"));
    }

    public String generateToken(UserEntity userEntity) {
        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("name", userEntity.getUsername())
                .claim("email", userEntity.getEmail())
                .claim("sub", userEntity.getUsername())
                .claim("jti", String.valueOf(userEntity.getId()))
                .claim("iat", Date.from(now))
                .claim("exp", Date.from(now.plus(expirationTime, ChronoUnit.MILLIS)))
                .signWith(hmacKey)
                .compact();
        log.debug(jwtToken);
        return jwtToken;
    }

    private Claims getAllClaimsFromToken(String token) {
        Jws<Claims> jwt = Jwts.parser().setSigningKey(hmacKey).build().parseClaimsJws(token);
        return jwt.getBody();
    }

    public String getSubjectFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private Date getExiprationDateFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExiprationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserEntity userEntity) {
        // 토큰 유효기간 체크
        if (isTokenExpired(token)) {
            return false;
        }

        // 토큰 내용을 검증
        String subject = getSubjectFromToken(token);
        String username = userEntity.getUsername();

        return subject != null && username != null && subject.equals(username);
    }

}
