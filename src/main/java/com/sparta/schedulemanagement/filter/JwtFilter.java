package com.sparta.schedulemanagement.filter;

import com.sparta.schedulemanagement.util.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {
    // validate -> 토큰 검증 API
    // 모든 기능을 사용하기 전에 validate 한번 요청 후에 기능과 관련한 API확인

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        System.out.println("DO FILTER ");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                String username = JwtTokenProvider.extractUsername(token);

                if (JwtTokenProvider.validateToken(token, username)) {
                    httpServletRequest.setAttribute("username", username);
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 유효하지 않습니다.");
                }
            } catch (JwtException e) {
                httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 유효하지 않습니다.");
            }
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "존재하지 않거나 유효하지 않은 Authorization 헤더");
        }
    }

    @Override
    public void destroy() {}
}