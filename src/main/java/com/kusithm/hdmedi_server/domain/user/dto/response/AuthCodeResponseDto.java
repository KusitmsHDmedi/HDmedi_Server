package com.kusithm.hdmedi_server.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthCodeResponseDto {
    private String authCode;

    public static AuthCodeResponseDto of(String authCode){
        return AuthCodeResponseDto.builder()
                .authCode(authCode)
                .build();
    }
}
