package com.kusithm.hdmedi_server.domain.user.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@Builder
@Getter
@RedisHash(value = "authCode", timeToLive = 604800000)
public class AuthCode {
    @Id
    private String authCode;
    private Long id;

    public static AuthCode createAuthCode(String authCode, Long id){
        return AuthCode.builder()
                .authCode(authCode)
                .id(id)
                .build();
    }
}
