package com.kusithm.hdmedi_server.domain.user.auth.naver;

import com.kusithm.hdmedi_server.global.error.exception.UnauthorizedException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.INVALID_NAVER_ACCESS_TOKEN;

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
