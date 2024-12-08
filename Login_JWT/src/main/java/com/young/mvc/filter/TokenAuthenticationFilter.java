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
public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final static String HEADER_AUTH = "Authorization";
	private final static String TOKEN_PREFIX = "Bearer ";
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 요청 헤더의 Authorization 키의 값 조회(토큰 조회)
		String authHeader = request.getHeader(HEADER_AUTH);
		// 가져온 값에서 접두사 제거(최종 토큰 값 조회)
		String token = null;
		if(authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
			token = authHeader.substring(TOKEN_PREFIX.length());
		}
		// 유효성 검증 
		if (token != null && jwtUtil.validate(token)) { // 로그인, 회원가입 시 token은 null인데 에러뜨지않나 -> 따로 api 제외해야함?
	        // 토큰 유효할 경우 다음 필터로 진행
	        filterChain.doFilter(request, response);
	    } else {
	        // 유효하지 않은 경우 처리 (중단됨)
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Unauthorized access.");
	        // return;
	    }
		
	}

}
