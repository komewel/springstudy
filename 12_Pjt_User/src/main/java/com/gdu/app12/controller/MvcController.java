package com.gdu.app12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
	
	@GetMapping(value={"/", "/index.do"}) // 매핑값을 두개주는 방법
	public String welcome() {
		return "index";
	}
}
