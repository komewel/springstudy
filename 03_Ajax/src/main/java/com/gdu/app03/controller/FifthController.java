package com.gdu.app03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app03.service.IFifthService;

@Controller
public class FifthController {

	@Autowired
	private IFifthService fifthService;
	
	@GetMapping(value="/papago.do", produces="application/json")
	public ResponseEntity<String> papago(HttpServletRequest request) {
		return fifthService.papago(request);
	}
	// 반환타입이 사실은 String이 아니라 json이다		
	// bean파일을 따로 안만들어줘도 바로 받을수 있구나
	
}
