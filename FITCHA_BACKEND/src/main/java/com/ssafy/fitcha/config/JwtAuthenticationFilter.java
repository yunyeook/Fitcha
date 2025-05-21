package com.ssafy.fitcha.config;

import com.ssafy.fitcha.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// JWT를 이용해 인증 처리를 담당하는 필터
// 매 요청마다 한 번 실행됨 (OncePerRequestFilter 상속)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // JwtUtil을 주입받아 토큰 검증과 유저 정보 추출에 사용
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 요청에서 JWT 토큰 추출
        String token = parseJwt(request);

        // 토큰이 있고, 유효하다면 인증 처리
        if (token != null && jwtUtil.validateToken(token)) {
            // 토큰에서 userId 추출
            String userId = jwtUtil.getUserId(token);

            // 인증 객체 생성 (실제 권한 정보는 null로 처리 – 필요시 추후 구현)
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, null);

            // 인증 객체에 요청 정보를 부여 (세부 정보 설정)
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

            // Spring Security의 Context에 인증 정보 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }

    // 요청 헤더에서 JWT 토큰 추출하는 메서드
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        // Authorization 헤더가 "Bearer "로 시작하는 경우에만 추출
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // "Bearer " 이후의 토큰만 반환
        }

        return null; // 토큰 없음
    }
}
