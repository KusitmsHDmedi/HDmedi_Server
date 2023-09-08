package com.kusithm.hdmedi_server.domain.user.controller;

import com.kusithm.hdmedi_server.domain.user.dto.request.UserAuthRequestDto;
import com.kusithm.hdmedi_server.domain.user.dto.response.UserAuthResponseDto;
import com.kusithm.hdmedi_server.domain.user.service.AuthService;
import com.kusithm.hdmedi_server.global.common.BaseResponse;
import com.kusithm.hdmedi_server.global.common.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@Controller
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<BaseResponse<?>> signIn(@RequestHeader("Authorization") final String token,
                                                  @RequestBody final UserAuthRequestDto userAuthRequestDto){

        final UserAuthResponseDto responseDto = authService.signIn(token, userAuthRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.of(SuccessCode.OK, responseDto));
    }
}
