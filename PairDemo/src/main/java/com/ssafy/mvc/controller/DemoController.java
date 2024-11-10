package com.ssafy.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.mvc.model.service.RecordService;
import com.ssafy.mvc.model.service.UserService;

@Controller
public class DemoController {
	
	private final UserService userService;
	private final RecordService recordService;

	@Autowired
	public DemoController(UserService userService, RecordService recordService) {
		this.userService = userService;
		this.recordService = recordService;
	}
	
	// user 확인
	@GetMapping("users")
	public String userList(Model model) {
		model.addAttribute("users", userService.getUserList());
		return "/user/checkUsers";
	}
	
	// user별 habit, record 확인
	@GetMapping("records")
	public String recordList(Model model, @RequestParam("userId") String userId) {
		model.addAttribute("records", recordService.getRecordList(userId));
		// System.out.println("Controller : "+recordService.getRecordList(userId));
		return "/user/checkUserRecords";
	}
	

}
