<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 상세보기</h2>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>이미지</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<tr>
			<td>${board.boardId }</td>
			<td>${board.title }</td>
			<td>${board.writer }</td>
			<td>${board.content }</td>
			<td>${board.img }</td>
			<td>${board.viewCnt }</td>
			<td>${board.regDate }</td>
		</tr>
	</table>
	
	<div>
		<h3>이미지 확인하기</h3>
		<img src="/img/${board.img }">
		<a href="/img/${board.img }">${board.img }</a>
		
		<h3>이미지 다운로드</h3>
		<a href="/download?fileName=${board.img }">클릭</a>
	</div>

	<a href="updateform?boardId=${board.boardId }">수정</a>
	<a href="delete?boardId=${board.boardId }">삭제</a>
	<a href="list">목록</a>
	<!-- form태그 사용해서 post로 요청 보낼 수도 있음 -->
</body>
</html>