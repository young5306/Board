<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/bootstrap.jsp" %>
</head>
<body>
	<div class="container">
		<form action="signup" method="POST">
			<div class="mb-3">
				<label for="name" class="form-label">이름</label> <input type="text"
					id="name" name="name" class="form-control">
			</div>
			<div class="mb-3">
				<label for="userId" class="form-label">아이디</label> <input type="text"
					id="userId" name="userId" class="form-control">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">비밀번호</label> <input
					type="password" id="password" name="password" class="form-control">
			</div>
			<div style="text-align: center; margin-top: 10px;">
				<button class="btn btn-primary">회원가입</button>
			</div>
		</form>
	</div>
</body>
</html>