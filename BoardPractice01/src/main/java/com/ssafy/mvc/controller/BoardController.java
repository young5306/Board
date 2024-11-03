package com.ssafy.mvc.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.mvc.model.dto.Board;
import com.ssafy.mvc.model.dto.SearchCondition;
import com.ssafy.mvc.model.service.BoardService;

@Controller
//@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	private final ResourceLoader resourceLoader;
	
	@Autowired
	public BoardController(BoardService boardService, ResourceLoader resourceLoader) {
		super();
		this.boardService = boardService;
		this.resourceLoader = resourceLoader;
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
	
	// 단일 이미지 업로드
	@PostMapping("write") 
	public String write(@RequestParam("file") MultipartFile file, Board board) throws IllegalStateException, IOException {
		
		// board에 filename 넣어서 service에 저장?
		if(file != null && file.getSize() > 0) { // 파일 객체가 실제 존재하는지, 파일이 존재하더라도 실제로 데이터가 있는지(빈파일이 아닌지)
			Resource resource = resourceLoader.getResource("classpath:/static/img"); // 이미지 저장할 폴더 (classpath = src/main)
			// System.out.println(file.getOriginalFilename());
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); // 중복 방지 시간+zzang.png
			file.transferTo(new File(resource.getFile(), fileName)); // 최종경로(경로+파일이름)로 이미지 저장 -> 실제로는 target 폴더안에서 확인할 수 있음
			board.setImg(fileName);
		}

		boardService.writeBoard(board);
		return "redirect:detail?boardId="+board.getboardId();
	}
	
	
	// 이미지 다운로드
	@GetMapping("/download")
	public String download(@RequestParam("fileName") String fileName, Model model) {
		// 파일 정보를 모델에 담고, 파일 다운로드를 위한 뷰인 FileDownLoadView로 fileInfo를 모델과 함께 넘겨줌
		model.addAttribute("fileName", fileName);
		return "fileDownloadView";
	}
	
	
	
	@GetMapping("updateform")
	public String updateform(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("board", boardService.getBoard(boardId));
		return "board/updateform";
	}
	
	@PostMapping("update")
	public String update(@RequestParam("file") MultipartFile file, @ModelAttribute Board board) throws IllegalStateException, IOException {
		
		if(file != null && file.getSize() > 0) {
			Resource resource = resourceLoader.getResource("classpath:/static/img"); 
			System.out.println(file.getOriginalFilename());
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); 
			file.transferTo(new File(resource.getFile(), fileName)); 
			board.setImg(fileName);
			System.out.println(fileName);
		}
		
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
