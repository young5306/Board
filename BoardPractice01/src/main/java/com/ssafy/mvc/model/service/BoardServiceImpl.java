package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.BoardDao;
import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;
	
	@Autowired
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List<Board> getBoardList() {
		return boardDao.selectAll();
	}

	@Override
	public Board getBoard(int boardId) {
		boardDao.updateViewCnt(boardId);
		return boardDao.selectOne(boardId);
	}

	@Override
	public void writeBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public void modifyBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Override
	public void removeBoard(int boardId) {
		boardDao.deleteBoard(boardId);
	}

	@Override
	public List<Board> search(SearchCondition condition) {
		return boardDao.search(condition);
	}

}
