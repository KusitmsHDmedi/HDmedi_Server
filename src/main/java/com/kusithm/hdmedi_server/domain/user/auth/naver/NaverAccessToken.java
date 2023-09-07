package com.kusithm.hdmedi_server.domain.user.auth.naver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NaverAccessToken {
    private static final String TOKEN_TYPE = "Bearer ";
    private String accessToken;

    public static NaverAccessToken of(String accessToken){
        return NaverAccessToken.builder().
                accessToken(accessToken).
                build();
    }

    public String getAccessTokenWithTokenType(){
        return TOKEN_TYPE + accessToken;
    }
}
