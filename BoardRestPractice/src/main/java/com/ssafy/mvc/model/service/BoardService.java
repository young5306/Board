package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.Board;

public interface BoardService {
	
	// 1. 게시글 전체조회 + 검색
	public List<Board> getBoardList();
	
	// 2. 게시글 상세보기
	public Board getBoard(int boardId);
	
	// 3. 게시글 등록
	public void writeBoard(Board board);
	
	// 4. 게시글 삭제
	public boolean removeBoard(int boardId);
	
	// 5. 게시글 수정
	public void modifyBoard(Board board);
	
	// 6. 파일 업로드

}
