package com.kusithm.hdmedi_server.domain.user.service;

import com.kusithm.hdmedi_server.domain.user.auth.naver.NaverOAuthProvider;
import com.kusithm.hdmedi_server.domain.user.domain.Platform;
import com.kusithm.hdmedi_server.domain.user.domain.User;
import com.kusithm.hdmedi_server.domain.user.dto.request.UserAuthRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.UserAuthResponseDto;
import com.kusithm.hdmedi_server.domain.user.repository.RefreshTokenRepository;
import com.kusithm.hdmedi_server.domain.user.repository.UserRepository;
import com.kusithm.hdmedi_server.global.config.jwt.JwtProvider;
import com.kusithm.hdmedi_server.global.config.jwt.Token;
import com.kusithm.hdmedi_server.global.error.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kusithm.hdmedi_server.domain.user.domain.RefreshToken.createRefreshToken;
import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final NaverOAuthProvider naverOAuthProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserAuthResponseDto signIn(String token, UserAuthRequestDto requestDto) {
        Platform platform = Platform.getEnumPlatformFrom(requestDto.getPlatform());
        String platformId = getPlatformId(token);
        User findUser = getUser(platform, platformId);
        Token issuedToken = issueAccessTokenAndRefreshToken(findUser);
        updateRefreshToken(issuedToken.getRefreshToken(), findUser);
        return UserAuthResponseDto.of(issuedToken, findUser);
    }

    private User getUser(Platform platform, String platformId) {
        return userRepository.findUserByPlatformAndPlatformId(platform, platformId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    private User saveUser(Platform platform, String platformId, String userName) {
        User createdUser = User.createUser(platform, platformId, userName);
        userRepository.save(createdUser);
        return createdUser;
    }

    private String getPlatformId(String token) {
        return naverOAuthProvider.getNaverPlatformId(token);
    }

    private Token issueAccessTokenAndRefreshToken(User user) {
        return jwtProvider.issueToken(user.getId());
    }

    private void updateRefreshToken(String refreshToken, User user) {
        refreshTokenRepository.save(createRefreshToken(user.getId(), refreshToken));
    }
}
