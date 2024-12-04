package com.young.mvc.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "JWT_LoginPractice_Young_SecretKey_123456789123456789";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	// 토큰 생성
	public String createToken(String loginId) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
		return Jwts.builder().header().add("typ", "JWT").and().claim("loginId", loginId).expiration(exp)
				.signWith(secretKey).compact();
	}

	// 유효성 검증
	public Jws<Claims> validate(String token) {
		return Jwts.parser().
				verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
	}

}
