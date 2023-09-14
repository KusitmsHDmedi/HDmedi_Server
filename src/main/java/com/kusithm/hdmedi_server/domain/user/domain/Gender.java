package com.kusithm.hdmedi_server.domain.user.domain;

import com.kusithm.hdmedi_server.global.error.exception.InvalidValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.INVALID_GENDER_TYPE;

@RequiredArgsConstructor
@Getter
public enum Gender {
    MAN("man"),
    WOMAN("woman");

    private final String stringGender;

    public static Gender getEnumGenderFrom(String stringGender) {
        return Arrays.stream(values())
                .filter(gender -> gender.stringGender.equals(stringGender))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(INVALID_GENDER_TYPE));
    }
}
