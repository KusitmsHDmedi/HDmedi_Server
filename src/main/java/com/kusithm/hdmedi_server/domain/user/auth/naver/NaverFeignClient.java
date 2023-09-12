package com.kusithm.hdmedi_server.domain.user.auth.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naver-feign-client", url = "https://openapi.naver.com/v1/nid/me")
public interface NaverFeignClient {
    @GetMapping
    NaverAccessTokenInfo getNaverAccessTokenInfo(@RequestHeader("Authorization") String accessToken);
}
