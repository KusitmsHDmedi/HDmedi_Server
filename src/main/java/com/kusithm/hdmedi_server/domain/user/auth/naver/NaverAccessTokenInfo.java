package com.kusithm.hdmedi_server.domain.user.auth.naver;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NaverAccessTokenInfo {
    private String accessToken;

    public static NaverAccessTokenInfo of(String accessToken) {
        return NaverAccessTokenInfo.builder()
                .accessToken(accessToken)
                .build();
    }
}
