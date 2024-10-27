package com.ssafy.mvc.model.dao;

import java.util.List;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;

public interface BoardDao {

	// 1. 게시글 전체 가져오기
	public List<Board> selectAll();
	// 2. 게시글 하나 가져오기
	public Board selectOne(int boardId);
	// 3. 게시글 등록
	public void insertBoard(Board board);
	// 4. 게시글 수정
	public void updateBoard(Board board);
	// 5. 게시글 삭제
	public void deleteBoard(int boardId);
	// 6. 조회수 증가
	public void updateViewCnt(int boardId);
	// 7. 검색 기능
	public List<Board> search(SearchCondition condition);

}
