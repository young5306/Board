package com.example.demo.util;

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
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	
	// jwt(토큰) 생성
	public String createToken(int userId, String username) {
		Date exp = new Date(System.currentTimeMillis()+100*60*60);
		return Jwts.builder() // base64url 인코딩 과정 포함됨
				.header() // 헤더 설정
				.add("typ", "JWT") // 헤더에 토큰 유형 추가
				.and()
				.claim("userId", userId) // 페이로드에 'userId' 클레임 추가
				.claim("username", username) 
				// 토큰은 클라이언트에 저장되고 전송되는 데이터라 보안상 이메일, 비밀번호 같은 개인 정보는 토큰에 넣지 X
				// 사용자 인증을 위해 식별 가능한 정보(주로 고유식별자 id)만 jwt에 포함시키는게 일반적
				.expiration(exp) // 페이로드에 만료 시간 추가
				.signWith(secretKey) // 비밀 키로 서명
				.compact(); // 최종적으로 JWT 생성
	}
	
	// 토큰 유효성 검증
	public Jws<Claims> validate(String token){
		return Jwts.parser()  // JWT를 파싱해서 헤더, 페이로드, 서명을 처리 
				.verifyWith(secretKey) // secretKey(서명 검증에 사용할 비밀 키)로 jwt의 서명 검증
				.build() // 빌더 패턴을 사용해 파서 객체를 최종적으로 설정
				.parseSignedClaims(token); // 실제로 jwt를 파싱하고, 서명 검증을 수행
		// 서명이 유효하면 헤더, 페이로드를 포함하는 Claims 객체를 반환
		// 서명 검증 실패 시 예외 발생
	}
}
