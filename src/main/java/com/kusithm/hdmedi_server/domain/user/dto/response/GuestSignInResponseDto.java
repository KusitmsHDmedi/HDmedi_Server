package com.kusithm.hdmedi_server.domain.user.dto.response;

import com.kusithm.hdmedi_server.domain.user.domain.Children;
import com.kusithm.hdmedi_server.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class GuestSignInResponseDto {
    private Long Id;
    private String childName;
    private String parentsName;
    private LocalDate birthday;
    private String accessToken;

    public static GuestSignInResponseDto of(User user, Children children, String accessToken){
        return GuestSignInResponseDto.builder()
                .Id(user.getId())
                .childName(children.getName())
                .parentsName(user.getUserName())
                .birthday(children.getBirthday())
                .accessToken(accessToken)
                .build();
    }
}
