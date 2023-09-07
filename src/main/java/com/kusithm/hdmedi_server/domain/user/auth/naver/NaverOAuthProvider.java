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
    private final NaverFeignClient naverFeignClient;

    public String getNaverPlatformId(String accessToken) {
        NaverAccessToken naverAccessToken = NaverAccessToken.of(accessToken);
        String accessTokenWithTokenType = naverAccessToken.getAccessTokenWithTokenType();
        NaverAccessTokenInfo naverAccessTokenInfo = getNaverAccessTokenInfo(accessTokenWithTokenType);
        return String.valueOf(naverAccessTokenInfo.getId());
    }

    private NaverAccessTokenInfo getNaverAccessTokenInfo(String accessTokenWithTokenType) {
        try {
            return naverFeignClient.getNaverAccessTokenInfo(accessTokenWithTokenType);
        } catch (FeignException e) {
            log.error("Feign Exception: ", e);
            throw new UnauthorizedException(INVALID_NAVER_ACCESS_TOKEN);
        }
    }

}
