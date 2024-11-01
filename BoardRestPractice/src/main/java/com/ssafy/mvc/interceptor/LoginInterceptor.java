package com.ssafy.mvc.interceptor;

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
		System.out.println("INTERCEPTOR");
		HttpSession session = request.getSession();
		
		request.getMethod();
		"GET".equalsIgnoreCase(request.getMethod());
		
		
		if(session.getAttribute("loginUser")==null) {
			return false;
		}
		return true;
	}
	

}
