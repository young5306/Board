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
	<%@ include file="../common/header.jsp"%>

	<table>
		<tr>
			<th>ID</th>
			<th>PW</th>
			<th>NAME</th>
			<th>CURRICULUM</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userId }</td>
				<td>${user.password }</td>
				<td>${user.name }</td>
				<td>${user.curriculumCode }</td>
			</tr>
		</c:forEach>
	</table>


	<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}">홈으로</a>

</body>
</html>