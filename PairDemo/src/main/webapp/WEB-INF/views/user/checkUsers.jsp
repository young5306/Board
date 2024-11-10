<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사용자 목록</h2>
	<table>
		<tr>
			<th>user_id</th>
			<th>username</th>
			<th>password</th>
			<th>email</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userId }</td>
				<td>${user.username }</td>
				<td>${user.password }</td>
				<td>${user.email }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>