package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.Board;

public interface BoardDao {
	
	// 1. 게시글 전체조회
	public List<Board> selectAll();

	// 2. 게시글 상세보기
	public Board selectOne(int boardId);

	// 3. 게시글 등록
	public void insertBoard(Board board);

	// 4. 게시글 삭제
	public int deleteBoard(int boardId);

	// 5. 게시글 수정
	public void updateBoard(Board board);
	
	// 6. 조회수 증가
	public void updateViewCnt(int boardId);
	
	// 7. 검색 기능
	

	// 8. 파일 업로드
}
