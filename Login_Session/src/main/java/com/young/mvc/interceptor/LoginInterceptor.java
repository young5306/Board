package com.young.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equals("OPTIONS")) {
			// CORS 프리플라이트 요청에 대해 허용 응답 설정
//            response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}

		HttpSession session = request.getSession();

		if(session.getAttribute("loginId") == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 응답 상태코드 설정
			response.getWriter().write("interceptor! please login"); 
			// response.getWriter()는 HTTP 응답의 본문을 작성할 수 있는 PrintWriter 객체를 반환
			// write()는 PrintWriter 객체를 사용하여 응답 본문에 덱스트 데이터를 씀 -> 프론트 err에 담김
			return false; // 다른 처리 없이 false만 반환하면 바디 없는 200ok 반환됨
		}
		return true;
	}
}
