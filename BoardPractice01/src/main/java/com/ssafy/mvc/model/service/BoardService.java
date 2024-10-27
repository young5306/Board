package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;

public interface BoardService {
	
	// 1. 게시글 전체 가져오기
	public List<Board> getBoardList();
	// 2. 게시글 하나 가져오기 + 조회수 증가
	public Board getBoard(int boardId);
	// 3. 게시글 등록
	public void writeBoard(Board board);
	// 4. 게시글 수정
	public void modifyBoard(Board board);
	// 5. 게시글 삭제
	public void removeBoard(int boardId);
	// 6. 검색
	public List<Board> search(SearchCondition condition);

}
