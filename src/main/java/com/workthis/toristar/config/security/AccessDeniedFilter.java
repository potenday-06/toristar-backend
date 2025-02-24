package com.workthis.toristar.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workthis.toristar.common.dto.ErrorResponse;
import com.workthis.toristar.common.exception.BaseErrorCode;
import com.workthis.toristar.common.exception.GlobalErrorCode;
import com.workthis.toristar.common.exception.ProjectCodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.workthis.toristar.common.consts.ProjectStatic.SwaggerPatterns;

@RequiredArgsConstructor
@Component
public class AccessDeniedFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return PatternMatchUtils.simpleMatch(SwaggerPatterns, servletPath);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ProjectCodeException e) {
            responseToClient(
                    response,
                    getErrorResponse(e.getErrorCode()));
        } catch (AccessDeniedException e) {
            // 익명 유저일 경우 ( 회원 가입 안하고 Role 자체가 어나니머스 )
            // basic authentication 같은경운
            //  ExceptionTranslateFilter 내부에서
            //  this.authenticationEntryPoint.commence(request, response, reason); 메소드를 실행시켜야함.
            //            Authentication authentication =
            // SecurityContextHolder.getContext().getAuthentication();
            //            boolean isAnonymous =
            // this.authenticationTrustResolver.isAnonymous(authentication);
            //            // ExceptionTranslateFilter 에게 처리 위임
            //            // 해야하는건.. 스웨거 일때만 해당하는걸로 수정해야함!
            //            if (isAnonymous) {
            //                throw e;
            //            }
            // 익명 유저가아닌 Access denied exception 같은경우 ( jwt 필터만 탄경우 )
            // 토큰 에러핸들링 제대로.
            ErrorResponse accessDenied =
                    new ErrorResponse(GlobalErrorCode.ACCESS_TOKEN_NOT_EXIST);
            responseToClient(response, accessDenied);
        }
    }

    private ErrorResponse getErrorResponse(BaseErrorCode errorCode) {
        return errorCode.getErrorResponse();
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
