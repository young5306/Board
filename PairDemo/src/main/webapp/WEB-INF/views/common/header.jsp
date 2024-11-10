<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<h2>로그인</h2>
<div>
	<c:if test="${empty loginUser }">
		<a href="login">로그인</a> <!-- GET -->
		<a href="signup">회원가입</a>
	</c:if>
	<c:if test="${not empty loginUser }">
		${loginUser}님 반갑습니다.
		<a href="/logout">로그아웃</a>
		<c:if test="${'admin' eq loginUser }">
			<a href="users">관리자페이지</a>
		</c:if>
	</c:if>
</div>
<hr>