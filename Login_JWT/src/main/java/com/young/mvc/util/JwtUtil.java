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

	/** 1. 토큰 생성
	 * 토큰 생성 토큰 생성시 다양한 데이터를 저장할 수 있음 (DTO or Map을 이용해 다양한 데이터 처리 가능)
	 * 
	 * @param loginId
	 * @return
	 */
	public String createToken(String loginId) {
		// 유효기간
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 유효기간 1시간 // currentTimeMillis - 현재 시간을 밀리초로
																			// 반환
		return Jwts.builder().header() // 헤더 설정
				.add("typ", "JWT") // 헤더에 'typ' 추가
				.and().claim("loginId", loginId) // 페이로드에 'loginId' 클레임 추가
				.expiration(exp) // 페이로드에 만료 시간 추가
				.signWith(secretKey) // 비밀 키로 서명
				.compact(); // 최종적으로 JWT 생성
		
		// Claim = Jwt Token에 들어갈 정보
        // Claim에 loginId를 넣어 줌으로써 나중에 loginId를 꺼낼 수 있음
	}

	/** 2. 유효성 검증
	 * 유효성 검증 실제로 내용물을 확인하기 위함은 아니고.. 이거 실행했을 때 에러나면 유효기간 지난거..
	 * 
	 * => filter에 걸렸을 때 에러를 처리하기 위해 boolean 반환으로 변경
	 * 
	 * @param token
	 * @return
	 */
	public boolean validate(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
			// 토큰이 만료되었는지 확인
            if (isExpired(claims)) {
                return false; // 만료된 토큰은 유효하지 않음
            }
			return true; // 검증 성공
		} catch (Exception e) {
			return false; // 검증 실패
		}
	}
    /** 3. 만료시간 검증
     * 
     * @param claims
     * @return
     */
    public boolean isExpired(Jws<Claims> claims) {
        Date expiration = claims.getPayload().getExpiration();
        return expiration.before(new Date()); // 만료시간이 현재 시간 이전이면 만료된 토큰
    }

}
