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
		<form action="write" method="POST" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="title" class="form-label">제목</label> <input type="text"
					id="title" name="title" class="form-control">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">작성자</label> <input
					type="text" id="writer" name="writer" class="form-control">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">내용</label>
				<textarea id="content" name="content" class="form-control"></textarea>
			</div>
			<!-- 단일 -->
			<div class="mb-3">
				<label for="file" class="form-label">이미지</label>
				<input type="file" id="file" name="file" class="form-control"><br>  
			</div>
			<!-- name을 Board의 필드명 img와 동일하게 쓰면 충돌나니까 file로 다르게 지정 -->
			<div style="text-align: right; margin-top: 10px;">
				<button class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
</body>
</html>