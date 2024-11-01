package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.model.dao.BoardDao;
import com.ssafy.mvc.model.dto.Board;

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
		return boardDao.selectOne(boardId);
	}

	@Override
	public void writeBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public boolean removeBoard(int boardId) {
		int result = boardDao.deleteBoard(boardId); // 건드린 레코드 수를 반환
		return result == 1;
	}

	@Override
	public void modifyBoard(Board board) {
		boardDao.updateBoard(board);
	}

}
