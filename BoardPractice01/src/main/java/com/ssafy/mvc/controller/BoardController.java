package com.ssafy.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;
import com.ssafy.mvc.model.service.BoardService;

@Controller
//@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("boards", boardService.getBoardList());
		return "/board/list";
	}
	
	@GetMapping("detail")
	public String detail(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("board", boardService.getBoard(boardId));
		return "/board/detail";
	}
	
	@GetMapping("writeform")
	public String writeform() {
		return "board/writeform";
	}
	
	@PostMapping("write")
	public String write(@ModelAttribute Board board) {
		boardService.writeBoard(board);
		return "redirect:detail?boardId="+board.getboardId();
	}
	
	@GetMapping("updateform")
	public String updateform(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("board", boardService.getBoard(boardId));
		return "board/updateform";
	}
	
	@PostMapping("update")
	public String update(@ModelAttribute Board board) {
		boardService.modifyBoard(board);
		return "redirect:detail?boardId="+board.getboardId();
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("boardId") int boardId) {
		boardService.removeBoard(boardId);
		return "redirect:list";
	}
	
	@GetMapping("search")
	public String search(@ModelAttribute SearchCondition condition, Model model) {
		model.addAttribute("boards", boardService.search(condition));
		return "/board/list";
	}
	
}
