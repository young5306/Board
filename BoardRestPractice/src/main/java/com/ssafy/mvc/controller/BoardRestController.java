package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.service.BoardService;

@RestController
@RequestMapping("/api-board")
public class BoardRestController {

	private final BoardService boardService;

	@Autowired
	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 1. 게시글 전체조회 + 검색
	@GetMapping("/board")
	public ResponseEntity<List<Board>> list() {
		List<Board> list = boardService.getBoardList();
		// System.out.println(list);
		// cf. 비어 있으면 NO CONTENT 활용 가능
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
		// return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 2. 게시글 상세보기
	@GetMapping("/board/{boardId}")
	public ResponseEntity<Board> detail(@PathVariable("boardId") int boardId){
		Board board = boardService.getBoard(boardId);
		// 게시글 없으면 Not Found 에러
		if(board == null) {
			// return ResponseEntity.notFound().build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		// System.out.println(board.toString());
		// return ResponseEntity.ok(board);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// 3. 게시글 등록
	@PostMapping("/board")
	// public ResponseEntity<?> write(@ModelAttribute Board board){ // -> 폼으로 전송
	public ResponseEntity<Board> write(@RequestBody Board board){ // -> json으로 전송
		boardService.writeBoard(board);
		System.out.println(board);
		// 이것도 등록됐는지 확인가능
		return new ResponseEntity<>(board, HttpStatus.CREATED);
	}

	// 4. 게시글 삭제
	@DeleteMapping("/board/{boardId}")
	public ResponseEntity<String> delete(@PathVariable("boardId") int boardId){
		boolean isDeleted = boardService.removeBoard(boardId);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Board deleted");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
	}

	// 5. 게시글 수정
	@PutMapping("/board/{boardId}") // id 전달안해도 됨
	public ResponseEntity<Void> update(@PathVariable("boardId") int boardId, @RequestBody Board board){
		board.setBoardId(boardId);
		boardService.modifyBoard(board);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 6. 파일 업로드

}
