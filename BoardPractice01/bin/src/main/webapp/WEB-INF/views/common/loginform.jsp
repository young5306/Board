<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<h2>로그인</h2>


<form action="login" method="POST">
	<label for="userId">아이디</label> <input type="text" id="userId"
		name="userId"> <label for="password">비밀번호</label> <input
		type="text" id="password" name="password">
	<button>로그인</button>
</form>
