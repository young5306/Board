<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/bootstrap.jsp"%>
</head>
<body>
	<div class="container">
		<form action="update" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="boardId" value="${board.boardId }">
			<div class="mb-3">
				<label for="title" class="form-label">제목</label> <input type="text"
					id="title" name="title" class="form-control" value="${board.title}">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">작성자</label> <input
					type="text" id="writer" name="writer" class="form-control"
					value="${board.writer}">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">내용</label>
				<textarea id="content" name="content" class="form-control">${board.content}</textarea>
			</div>
			<div class="mb-3">
				<label for="file" class="form-label">이미지</label> 
				<img src="/img/${board.img}" alt="기본 이미지">
				<input type="file"
					id="file" name="file" class="form-control" value="${board.img}"><br>
			</div>
			<div style="text-align: right; margin-top: 10px;">
				<button class="btn btn-primary">수정</button>
			</div>
		</form>
	</div>
</body>
</html>
