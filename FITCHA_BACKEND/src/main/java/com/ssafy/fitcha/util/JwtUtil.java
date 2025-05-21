package com.ssafy.fitcha.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

//@Component
//public class JwtUtil {
//	private String key = "SSAFY_fitcha_token_SecretKey";
//	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
//	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
//	
//
//}
