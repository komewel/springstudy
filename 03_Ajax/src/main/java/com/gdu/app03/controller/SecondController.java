package com.gdu.app03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BmiVo> bmi1(HttpServletRequest request){
		return secondService.execute1(request);
		// 응답을 해주는 response인수는 굳이 안써도 되므로 뺐다
	}
	
	@GetMapping(value="/second/bmi2") // produces가 없음에 주의합니다. (반환 객체 ResponseEntity에 Content-Type을 작성해서 보냅니다.), 그동안은 그냥 컨트롤러에서 작성을 했는데, 다른데서 작성이 가능하다.
	public ResponseEntity<Map<String, Object>> bmi2(BmiVo bmiVO) {
		return secondService.execute2(bmiVO);
	}
	/*
	@ResponseBody
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE)  // MediaType.APPLICATION_JSON_VALUE는 "application/json"이다.
	public BmiVo bmi1(HttpServletRequest request, HttpServletResponse response) {
		return secondService.execute1(request, response);
	} // jackson이 bean을 받아서 produces=MediaType.APPLICATION_JSON_VALUE을 참고해서 원하는 타입으로 응답해준다. 
	
	@ResponseBody
	@GetMapping(value="/second/bmi2", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> bmi2(BmiVo bmiVO) {
		return secondService.execute2(bmiVO);
	}
	 */
}
