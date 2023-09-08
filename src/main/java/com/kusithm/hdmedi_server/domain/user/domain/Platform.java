package com.kusithm.hdmedi_server.domain.user.domain;

import com.kusithm.hdmedi_server.global.error.exception.InvalidValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.INVALID_PLATFORM_TYPE;

@RequiredArgsConstructor
@Getter
public enum Platform {
    NAVER("naver"),
    WITHDRAW("withdraw");

    private final String stringPlatform;

    public static Platform getEnumPlatformFrom(String stringPlatform) {
        return Arrays.stream(values())
                .filter(platform -> platform.stringPlatform.equals(stringPlatform))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(INVALID_PLATFORM_TYPE));
    }
}
