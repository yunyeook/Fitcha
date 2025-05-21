package com.ssafy.fitcha.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
	private String key = "SSAFY_fitcha_project_token_SecretKey_!@#$%!@$";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간 설정, 보통은 20~30분이라는데 테스트하기 편하게 길게 설정함

	public String createToken(String userId) { // userId : DB에 저장되는 유저 고유 식별 번호를 의미
		Date now = new Date(); // 현재 시간
		Date exp = new Date(now.getTime() + EXPIRATION_TIME); // 만료 시간

		return Jwts.builder().setSubject(userId) // 토큰의 subject에 userId 설정
				.setIssuedAt(now) // 토큰 발급 시간
				.expiration(exp) // 토큰 만료 시간
				.signWith(secretKey) // 비밀키로 서명
				.compact(); // 최종적으로 문자열 토큰 반환
	}

	// 토큰 유효성 체크 메서드 추가
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 토큰에서 userId 꺼내기
	public String getUserId(String token) {
		return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
	}

}

