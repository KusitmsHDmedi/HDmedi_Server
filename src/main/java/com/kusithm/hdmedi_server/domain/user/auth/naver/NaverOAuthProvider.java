package com.kusithm.hdmedi_server.domain.user.auth.naver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NaverOAuthProvider {
    public String getNaverPlatformId(String accessToken) {
        NaverAccessToken naverAccessToken = NaverAccessToken.of(accessToken);
        String accessTokenWithTokenType = naverAccessToken.getAccessTokenWithTokenType();
        NaverAccessTokenInfo naverAccessTokenInfo = NaverAccessTokenInfo.of(accessTokenWithTokenType);
        return naverAccessTokenInfo.getAccessToken();
    }

}
