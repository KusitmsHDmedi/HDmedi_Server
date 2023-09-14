package com.kusithm.hdmedi_server.global.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kusithm.hdmedi_server.global.error.dto.ErrorBaseResponse;
import com.kusithm.hdmedi_server.global.error.exception.ErrorCode;
import com.kusithm.hdmedi_server.global.error.exception.InvalidValueException;
import com.kusithm.hdmedi_server.global.error.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter 단계에서 발생할 수 있는 예외를 처리하는 객체입니다.
 * JwtAuthenticationFilter에서 발생할 수 있는 예외를 관리하는 객체라고 생각해주시면 될 것 같습니다.
 * SecurityConfig에서 JwtAuthenticationFilter를 등록 후 해당하는 예외를 관리하기 위해 등록합니다.
 */
@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            handleUnauthorizedException(response, e);
        } catch (Exception ee) {
            handleException(response);
        }
    }

    private void handleUnauthorizedException(HttpServletResponse response, Exception e) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        if (e instanceof UnauthorizedException ue) {
            response.setStatus(ue.getErrorCode().getHttpStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(ErrorBaseResponse.of(ue.getErrorCode())));
        } else if (e instanceof InvalidValueException ie) {
            response.setStatus(ie.getErrorCode().getHttpStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(ErrorBaseResponse.of(ie.getErrorCode())));
        }
    }

    private void handleException(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(ErrorBaseResponse.of(ErrorCode.INTERNAL_SERVER_ERROR)));
    }
}
