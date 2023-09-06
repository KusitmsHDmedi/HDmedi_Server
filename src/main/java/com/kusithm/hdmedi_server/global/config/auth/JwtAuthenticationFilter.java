package com.kusithm.hdmedi_server.global.config.auth;

import com.kusithm.hdmedi_server.global.config.jwt.JwtProvider;
import com.kusithm.hdmedi_server.global.error.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.kusithm.hdmedi_server.global.error.exception.ErrorCode.INVALID_ACCESS_TOKEN;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = getAccessTokenFrom(request);
        jwtProvider.validateAccessToken(accessToken);
        final Long userId = jwtProvider.getSubject(accessToken);
        setAuthentication(request, userId);
        filterChain.doFilter(request, response);
    }

    private String getAccessTokenFrom(HttpServletRequest request) {
        String accessToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(accessToken) && accessToken.startsWith(BEARER))
            return accessToken.substring(BEARER.length());
        throw new UnauthorizedException(INVALID_ACCESS_TOKEN);
    }

    private void setAuthentication(HttpServletRequest request, Long userId) {
        UserAuthentication authentication = new UserAuthentication(userId, null, null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
