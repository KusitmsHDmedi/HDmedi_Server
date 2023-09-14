package com.kusithm.hdmedi_server.domain.user.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserSignUpRequestDto {
    private String userName;
    private String childrenName;
    private LocalDate birthday;
    private String gender;
    private String platform;
}
