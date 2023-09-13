package com.kusithm.hdmedi_server.domain.user.service;

import com.kusithm.hdmedi_server.domain.user.auth.naver.NaverOAuthProvider;
import com.kusithm.hdmedi_server.domain.user.domain.AuthCode;
import com.kusithm.hdmedi_server.domain.user.domain.Platform;
import com.kusithm.hdmedi_server.domain.user.domain.User;
import com.kusithm.hdmedi_server.domain.user.dto.request.UserAuthRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.request.UserSignUpRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.AuthCodeResponseDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.UserAuthResponseDto;
import com.kusithm.hdmedi_server.domain.user.repository.AuthCodeRepository;
import com.kusithm.hdmedi_server.domain.user.repository.RefreshTokenRepository;
import com.kusithm.hdmedi_server.domain.user.repository.UserRepository;
import com.kusithm.hdmedi_server.global.config.jwt.JwtProvider;
import com.kusithm.hdmedi_server.global.config.jwt.Token;
import com.kusithm.hdmedi_server.global.error.exception.ConflictException;
import com.kusithm.hdmedi_server.global.error.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static com.kusithm.hdmedi_server.domain.user.domain.RefreshToken.createRefreshToken;
import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.DUPLICATE_USER;
import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final NaverOAuthProvider naverOAuthProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthCodeRepository authCodeRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    public UserAuthResponseDto signIn(String token, UserAuthRequestDto requestDto) {
        Platform platform = Platform.getEnumPlatformFrom(requestDto.getPlatform());
        String platformId = getPlatformId(token);
        User findUser = getUser(platform, platformId);
        Token issuedToken = issueAccessTokenAndRefreshToken(findUser);
        updateRefreshToken(issuedToken.getRefreshToken(), findUser);
        return UserAuthResponseDto.of(issuedToken, findUser);
    }

    public UserAuthResponseDto signUp(String token, UserSignUpRequestDto requestDto) {
        Platform platform = Platform.getEnumPlatformFrom(requestDto.getPlatform());
        String platformId = getPlatformId(token);
        validateDuplicateUser(platform, platformId);
        User saveUser = saveUser(platform, platformId, requestDto.getUserName());
        Token issuedToken = issueAccessTokenAndRefreshToken(saveUser);
        updateRefreshToken(issuedToken.getRefreshToken(), saveUser);
        return UserAuthResponseDto.of(issuedToken, saveUser);
    }

    public AuthCodeResponseDto createAuthCode(Long userId) {
        String authCode = createAuthCodeAtSecureRandom(new SecureRandom());
        AuthCode createdAuthCode = AuthCode.createAuthCode(authCode, userId);
        saveAuthCode(createdAuthCode);
        return AuthCodeResponseDto.of(createdAuthCode.getAuthCode());
    }

    private void saveAuthCode(AuthCode createdAuthCode) {
        authCodeRepository.save(createdAuthCode);
    }

    private boolean duplicateAuthCode(String authCode) {
        return authCodeRepository.existsByAuthCode(authCode);
    }

    private String createAuthCodeAtSecureRandom(SecureRandom random) {
        StringBuilder codeBuilder;
        do {
            codeBuilder  = createAuthCodeWithStringBuilder(random);
        } while (duplicateAuthCode(codeBuilder.toString()));
        return codeBuilder.toString();
    }

    private StringBuilder createAuthCodeWithStringBuilder(SecureRandom random){
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder;
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

    private void validateDuplicateUser(Platform platform, String platformId) {
        if (userRepository.existsUserByPlatformAndPlatformId(platform, platformId))
            throw new ConflictException(DUPLICATE_USER);
    }
}
