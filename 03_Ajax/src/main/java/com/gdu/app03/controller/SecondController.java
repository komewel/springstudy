package com.gdu.app03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.BmiVo;
import com.gdu.app03.service.ISecondService;

@Controller
public class SecondController {

	private ISecondService secondService;
	
	@Autowired // 생성자에서 @Autowired는 생략할 수 있다, 생성자에 @Autowired를 할시 필드에도 자동으로 알아서 값이 들어간다,생성자를 만드는 대신에 롬복으로 @AllargsConstructo으로 만들어도 똑같은 기능을 한다 스프링이 알아서 생성자에는 @Autowired를 어짜피 생략해도 알아서 기능한다.
	public SecondController(ISecondService secondService) {
		super();
		this.secondService = secondService;
	}

	@ResponseBody
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE)  // MediaType.APPLICATION_JSON_VALUE는 "application/json"이다.
	public BmiVo bmi1(HttpServletRequest request, HttpServletResponse response) {
		return secondService.execute1(request, response);
	}
	
	@ResponseBody
	@GetMapping(value="/second/bmi2", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> bmi2(BmiVo bmiVO) {
		return secondService.execute2(bmiVO);
	}
}
