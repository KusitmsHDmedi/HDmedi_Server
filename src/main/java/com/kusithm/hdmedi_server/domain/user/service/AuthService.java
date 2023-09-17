package com.kusithm.hdmedi_server.domain.user.service;

import com.kusithm.hdmedi_server.domain.user.auth.naver.NaverOAuthProvider;
import com.kusithm.hdmedi_server.domain.user.domain.*;
import com.kusithm.hdmedi_server.domain.user.dto.request.UserAuthRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.request.UserSignUpRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.AuthCodeResponseDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.GuestSignInResponseDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.UserAuthResponseDto;
import com.kusithm.hdmedi_server.domain.user.repository.AuthCodeRepository;
import com.kusithm.hdmedi_server.domain.user.repository.ChildrenRepository;
import com.kusithm.hdmedi_server.domain.user.repository.RefreshTokenRepository;
import com.kusithm.hdmedi_server.domain.user.repository.UserRepository;
import com.kusithm.hdmedi_server.global.common.HDmediUser;
import com.kusithm.hdmedi_server.global.config.jwt.JwtProvider;
import com.kusithm.hdmedi_server.global.config.jwt.Token;
import com.kusithm.hdmedi_server.global.error.exception.ConflictException;
import com.kusithm.hdmedi_server.global.error.exception.EntityNotFoundException;
import com.kusithm.hdmedi_server.global.error.exception.UnauthorizedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;

import static com.kusithm.hdmedi_server.domain.user.domain.RefreshToken.createRefreshToken;
import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.*;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final NaverOAuthProvider naverOAuthProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthCodeRepository authCodeRepository;
    private final ChildrenRepository childrenRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    public UserAuthResponseDto signIn(String token, UserAuthRequestDto requestDto) {
        Platform platform = Platform.getEnumPlatformFrom(requestDto.getPlatform());
        String platformId = getPlatformId(token);
        User findUser = getUserFromPlatformAndPlatformId(platform, platformId);
        Token issuedToken = issueAccessTokenAndRefreshToken(findUser, Boolean.FALSE);
        updateRefreshToken(issuedToken.getRefreshToken(), findUser);
        return UserAuthResponseDto.of(issuedToken, findUser, findUser.getChildren());
    }

    public UserAuthResponseDto signUp(String token, UserSignUpRequestDto requestDto) {
        Platform platform = Platform.getEnumPlatformFrom(requestDto.getPlatform());
        String platformId = getPlatformId(token);
//        validateDuplicateUser(platform, platformId);
        User saveUser = saveUser(platform, platformId, requestDto.getUserName());
        Children saveChildren = saveChildren(saveUser, requestDto.getChildrenName(), requestDto.getGender(), requestDto.getBirthday());
        Token issuedToken = issueAccessTokenAndRefreshToken(saveUser, Boolean.FALSE);
        updateRefreshToken(issuedToken.getRefreshToken(), saveUser);
        return UserAuthResponseDto.of(issuedToken, saveUser, saveChildren);
    }

    public AuthCodeResponseDto createAuthCode(HDmediUser hDmediUser) {
        String authCode = createAuthCodeAtSecureRandom(new SecureRandom());
        AuthCode createdAuthCode = AuthCode.createAuthCode(authCode, hDmediUser.getId());
        saveAuthCode(createdAuthCode);
        return AuthCodeResponseDto.of(createdAuthCode.getId());
    }

    public GuestSignInResponseDto geustSignIn(String authCode) {
        AuthCode findAuthCode = getUserFromAuthCode(authCode);
        User findUser = getUserFromId(findAuthCode.getValue());
        Token issuedToken = issueAccessTokenAndRefreshToken(findUser, Boolean.TRUE);
        return GuestSignInResponseDto.of(findUser, findUser.getChildren(), issuedToken.getAccessToken());
    }

    private User getUserFromId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND));
    }

    private AuthCode getUserFromAuthCode(String authCode) {
        String AuthCodeId = jwtProvider.deletePrefixOfToken(authCode);
        return authCodeRepository.findById(AuthCodeId)
                .orElseThrow(() -> new UnauthorizedException(INVALID_AUTH_CODE));
    }

    private void saveAuthCode(AuthCode createdAuthCode) {
        authCodeRepository.save(createdAuthCode);
    }

    private boolean duplicateAuthCode(String authCode) {
        return authCodeRepository.existsById(authCode);
    }

    private String createAuthCodeAtSecureRandom(SecureRandom random) {
        StringBuilder codeBuilder;
        do {
            codeBuilder = createAuthCodeWithStringBuilder(random);
        } while (duplicateAuthCode(codeBuilder.toString()));
        return codeBuilder.toString();
    }

    private StringBuilder createAuthCodeWithStringBuilder(SecureRandom random) {
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder;
    }

    private Children saveChildren(User user, String name, String stringGender, LocalDate birthday) {
        Gender enumGender = Gender.getEnumGenderFrom(stringGender);
        Children createdChildren = Children.createChildren(user, name, enumGender, birthday);
        childrenRepository.save(createdChildren);
        return createdChildren;
    }

    private User getUserFromPlatformAndPlatformId(Platform platform, String platformId) {
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

    private Token issueAccessTokenAndRefreshToken(User user, Boolean isGuest) {
        HDmediUser hDmediUser = HDmediUser.of(user.getId(), isGuest);
        return jwtProvider.issueToken(hDmediUser);
    }

    private void updateRefreshToken(String refreshToken, User user) {
        refreshTokenRepository.save(createRefreshToken(user.getId(), refreshToken));
    }

    private void validateDuplicateUser(Platform platform, String platformId) {
        if (userRepository.existsUserByPlatformAndPlatformId(platform, platformId))
            throw new ConflictException(DUPLICATE_USER);
    }
}
