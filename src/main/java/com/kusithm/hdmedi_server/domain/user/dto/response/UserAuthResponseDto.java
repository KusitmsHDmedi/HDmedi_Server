package com.kusithm.hdmedi_server.domain.user.dto.response;

import com.kusithm.hdmedi_server.domain.user.domain.Children;
import com.kusithm.hdmedi_server.domain.user.domain.User;
import com.kusithm.hdmedi_server.global.config.jwt.Token;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserAuthResponseDto {
    private Long userId;
    private String children;
    private String accessToken;
    private String refreshToken;

    public static UserAuthResponseDto of(Token token, User user, Children children) {
        return UserAuthResponseDto.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .userId(user.getId())
                .children(children.getName())
                .build();
    }
}
