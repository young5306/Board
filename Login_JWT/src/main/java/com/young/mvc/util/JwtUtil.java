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
	// 토큰 생성시 다양한 데이터를 저장할 수 있음 (DTO or Map을 이용해 다양한 데이터 처리 가능)
	public String createToken(String loginId) {
		// 유효기간
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 유효기간 1시간 // currentTimeMillis - 현재 시간을 밀리초로 반환
		return Jwts.builder()
				.header() // 헤더 설정
				.add("typ","JWT") // 헤더에 'typ' 추가
				.and()
				.claim("loginId", loginId) // 페이로드에 'name' 클레임 추가
				.expiration(exp) // 페이로드에 만료 시간 추가
				.signWith(secretKey) // 비밀 키로 서명
				.compact(); // 최종적으로 JWT 생성
	}

	// 유효성 검증 
	// 실제로 내용물을 확인하기 위함은 아니고..
	// 이거 실행했을 때 에러나면 유효기간 지난거..
	public Jws<Claims> validate(String token){
		return Jwts.parser() // JWT를 파싱해서 헤더, 페이로드, 서명을 처리 
		.verifyWith(secretKey) // secretKey(서명 검증에 사용할 비밀 키)로 jwt의 서명 검증
	  .build() // 빌더 패턴을 사용해 파서 객체를 최종적으로 설정
		.parseSignedClaims(token); // 실제로 jwt를 파싱하고, 서명 검증을 수행
		// 서명이 유효하면 헤더, 페이로드를 포함하는 Claims 객체를 반환
		// 서명 검증 실패 시 예외 발생
	}

}
