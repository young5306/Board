<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
</head>
<body>

${loginUser }
	<h2>게시글 목록</h2>
	<%@ include file="../common/loginform.jsp" %>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${boards}" var="board">
			<tr>
				<td>${board.boardId }</td> <!-- 게터로 가져오기 -->
				<td><a href="detail?boardId=${board.boardId }">${board.title }</a></td>
				<td>${board.writer }</td>
				<td>${board.content }</td>
				<td>${board.viewCnt }</td>
				<td>${board.regDate }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>