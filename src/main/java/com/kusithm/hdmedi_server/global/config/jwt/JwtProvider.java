package com.kusithm.hdmedi_server.global.config.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kusithm.hdmedi_server.global.common.HDmediUser;
import com.kusithm.hdmedi_server.global.error.exception.ErrorCode;
import com.kusithm.hdmedi_server.global.error.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Getter
@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-token-expire-time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refresh-token-expire-time}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    public Token issueToken(HDmediUser hDmediUser) {
        Token responseToken;
        try {
            responseToken = Token.of(generateToken(hDmediUser, true), generateToken(hDmediUser, false));
        } catch (JsonProcessingException e) {
            throw new UnauthorizedException(ErrorCode.NOT_MATCH_OBJECT_TYPE);
        }
        return responseToken;
    }

    public String deletePrefixOfToken(String token){
        return StringUtils.delete(token, "Bearer ");
    }

    public void validateAccessToken(String accessToken) {
        try {
            getJwtParser().parseClaimsJws(accessToken);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new UnauthorizedException(ErrorCode.INVALID_ACCESS_TOKEN_VALUE);
        }
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            getJwtParser().parseClaimsJws(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorCode.EXPIRED_REFRESH_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new UnauthorizedException(ErrorCode.INVALID_REFRESH_TOKEN_VALUE);
        }
    }

    public void equalsRefreshToken(String providedRefreshToken, String storedRefreshToken) {
        if (!providedRefreshToken.equals(storedRefreshToken)) {
            throw new UnauthorizedException(ErrorCode.NOT_MATCH_REFRESH_TOKEN);
        }
    }

    public HDmediUser getSubject(String token) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getJwtParser().parseClaimsJws(token)
                .getBody()
                .getSubject(), HDmediUser.class);
    }

    private String generateToken(HDmediUser hDmediUser, boolean isAccessToken) throws JsonProcessingException {
        final Date now = new Date();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Date expiration = new Date(now.getTime() + (isAccessToken ? ACCESS_TOKEN_EXPIRE_TIME : REFRESH_TOKEN_EXPIRE_TIME));
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(objectMapper.writeValueAsString(hDmediUser))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
    }

    private Key getSigningKey() {
        String encoded = Base64.getEncoder().encodeToString(secretKey.getBytes());
        return Keys.hmacShaKeyFor(encoded.getBytes());
    }
}
