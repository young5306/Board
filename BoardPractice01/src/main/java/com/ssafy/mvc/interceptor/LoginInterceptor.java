package com.ssafy.mvc.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("loginUser"));
		System.out.println("uri : "+ request.getRequestURI());
		if(session.getAttribute("loginUser") == null) {
			response.sendRedirect("login");
			return false;
		}
		return true;
	}
		
}
