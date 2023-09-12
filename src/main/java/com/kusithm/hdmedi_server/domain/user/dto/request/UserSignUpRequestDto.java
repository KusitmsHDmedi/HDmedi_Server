package com.kusithm.hdmedi_server.domain.user.dto.request;

import com.kusithm.hdmedi_server.domain.user.domain.Gender;
import com.kusithm.hdmedi_server.domain.user.domain.Platform;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserSignUpRequestDto {
    private String userName;
    private LocalDateTime birthday;
    private String gender;
    private String platform;
}
