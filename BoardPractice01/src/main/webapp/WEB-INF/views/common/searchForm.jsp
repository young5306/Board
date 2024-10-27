<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="search" method="GET">

	<label for="key">검색기준</label>
	<select id="key" name="key"> <!--  select는 name으로, option은 value로 값 전달  -->
		<option value="none" selected="selected">없음</option> <!-- 페이지 로드 시 선택되어있음 -->
		<option value="writer">작성자</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>
	
	<label for="word">검색내용</label>
	<input type="text" id="word" name="word">
	
	<label for="orderBy">정렬기준</label>
	<select id="orderBy" name="orderBy"> 
		<option value="none" selected>없음</option> 
		<option value="writer">작성자</option>
		<option value="title">제목</option>
		<option value="view_cnt">조회수</option>
	</select> 
	
	<label for="orderByDir">정렬방향</label>
	<select id="orderByDir" name="orderByDir">
		<option value="asc">오름차순</option>
		<option value="desc">내림차순</option>
	</select> 
	
	<input type="submit" value="검색">
</form>