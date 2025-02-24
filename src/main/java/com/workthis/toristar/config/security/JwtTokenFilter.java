package com.workthis.toristar.config.security;

import com.workthis.toristar.common.consts.ProjectStatic;
import com.workthis.toristar.common.dto.AccessTokenInfo;
import com.workthis.toristar.common.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        // 쿠키방식 지원
//        Cookie accessTokenCookie = WebUtils.getCookie(request, "idToken");
//        if (accessTokenCookie != null) {
//            return accessTokenCookie.getValue();
//        }
        // 기존 jwt 방식 지원
        String rawHeader = request.getHeader(ProjectStatic.AUTH_HEADER);

        if (rawHeader != null
                && rawHeader.length() > ProjectStatic.BEARER.length()
                && rawHeader.startsWith(ProjectStatic.BEARER)) {
            return rawHeader.substring(ProjectStatic.BEARER.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        AccessTokenInfo accessTokenInfo = jwtTokenProvider.parseAccessToken(token);

        UserDetails userDetails =
                new AuthDetails(accessTokenInfo.getMemberId().toString(), "ROLE_USER");
        return new UsernamePasswordAuthenticationToken(
                userDetails, "toristar", userDetails.getAuthorities());
    }
}
