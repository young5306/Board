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

	<h2>사용자 레코드 목록</h2>
	<table>
		<tr>
			<th>record_id</th>
			<th>user_id</th>
			<th>habit_id</th>
			<th>date</th>
		</tr>
		<c:forEach items="${records}" var="record">
			<tr>
				<td>${record.recordId }</td>
				<td>${record.userId }</td>
				<td>${record.habitId }</td>
				<td>${record.date }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>