package com.kusithm.hdmedi_server.domain.user.auth.naver;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class NaverAccessTokenInfo {
    private String accessToken;

    public static NaverAccessTokenInfo of(String accessToken){
        return NaverAccessTokenInfo.builder()
                .accessToken(accessToken)
                .build();
    }
}
