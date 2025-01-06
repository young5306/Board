package com.young.mvc.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.young.mvc.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final static String HEADER_AUTH = "Authorization";
	private final static String TOKEN_PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		// 1. 요청 헤더의 Authorization 키의 값 조회(토큰 조회)
		String authHeader = request.getHeader(HEADER_AUTH);
		// String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		// 2. Authorization 값 검증
		// Header의 Authorization 값이 비어있거나 'Bearer '로 시작하지 않으면 => 잘못된 토큰 => 로그인x상태로 요청 진행?
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		// 3. 전송받은 값에서 'Bearer ' 뒷부분(Jwt Token) 추출
		String token = authHeader.substring(TOKEN_PREFIX.length());
		// String token = authorizationHeader.split(" ")[1];
		
		// 4. 유효성 검증
		// 전송받은 Jwt Token이 만료되었으면 => 다음 필터 진행(인증 X)
        if(!jwtUtil.validate(token)) {
        	// 유효하지 않은 토큰인 경우 응답 처리 후 종료
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("유효하지 않은 토큰");
            return;
        }

	}

}
