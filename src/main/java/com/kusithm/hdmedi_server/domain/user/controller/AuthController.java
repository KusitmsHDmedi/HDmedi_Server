package com.kusithm.hdmedi_server.domain.user.controller;

import com.kusithm.hdmedi_server.domain.user.dto.request.UserAuthRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.request.UserSignUpRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.AuthCodeResponseDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.GuestSignInResponseDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.UserAuthResponseDto;
import com.kusithm.hdmedi_server.domain.user.service.AuthService;
import com.kusithm.hdmedi_server.global.common.BaseResponse;
import com.kusithm.hdmedi_server.global.common.HDmediUser;
import com.kusithm.hdmedi_server.global.common.SuccessCode;
import com.kusithm.hdmedi_server.global.config.auth.AuthenticatedUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/authCode")
    public ResponseEntity<BaseResponse<?>> createAuthCode(@AuthenticatedUserId final HDmediUser hDmediUser) {
        System.out.println(hDmediUser.getId() + "+" + hDmediUser.getIsGuest());
        final AuthCodeResponseDto responseDto = authService.createAuthCode(hDmediUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, responseDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<BaseResponse<?>> signIn(@RequestHeader("Authorization") final String token,
                                                  @RequestBody final UserAuthRequestDto userAuthRequestDto) {
        final UserAuthResponseDto responseDto = authService.signIn(token, userAuthRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, responseDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<BaseResponse<?>> signUp(@RequestHeader("Authorization") final String token,
                                                  @RequestBody final UserSignUpRequestDto userSignUpRequestDto) {
        final UserAuthResponseDto responseDto = authService.signUp(token, userSignUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.of(SuccessCode.CREATED, responseDto));
    }

    @GetMapping("/signIn/guest")
    public ResponseEntity<BaseResponse<?>> guestSignIn(@RequestHeader("Authorization") final String authCode) {
        final GuestSignInResponseDto responseDto = authService.geustSignIn(authCode);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.CREATED, responseDto));
    }
}
