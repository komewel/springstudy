package com.gdu.app12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/agree.form")
	public String agreeForm() {
		return "user/agree";
	}
	
	@GetMapping("/join.form") 
	public String joinForm(@RequestParam(value="location", required=false) String location // 필수로 와야 할 값이 아니므로 required=false 처리를 해줘야 한다 안오면 빈문자열이 저장된다
						, @RequestParam(value="event", required=false) String event		  // 파라미터 location, event이 전달되지 않으면 빈 문자열("") 이 String location에 저장된다
						, Model model) {
		model.addAttribute("location", location);
		model.addAttribute("event", event);
		return "user/join";
	}
}
