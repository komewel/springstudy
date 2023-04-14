package com.gdu.app03.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gdu.app03.domain.Contact;
import com.gdu.app03.service.IThirdService;

@Controller
public class ThirdController {
	
	//field
	private IThirdService thirdService;  //rootcontext랑 id를 맞추는게 좋다 bean태그로 만든 
										 // method(setter형식의), 생성자, 필드로 
	// setter method
	@Autowired
	public void method(IThirdService thirdService) { // Spring Container에서 IThirdService 타입의 Bean을 찾아서 매개변수에 주입한다.
		this.thirdService = thirdService; 
	}
	
	@PostMapping(value="/third/ajax1", produces="application/json")
	public ResponseEntity<Contact> ajax1(@RequestBody Contact contact){ // 요청 본문(request body)에 포함된 JSON데이터를(jackson이) Contact contact 객체에 저장해 주세요, 
																		// @RequestBody파라미터없이 데이터를 주고받는 방식(나름 고도화된 방식)
		return thirdService.execute1(contact);							// ResponseEntity가 있으면 @responseBody를 사용하지 않아도 알아서 기능해준다.		
	}
	
	@PostMapping(value="/third/ajax2", produces="application/json")
	public ResponseEntity<Map<String, String>> ajax2(@RequestBody Map<String, String> map) {
		return thirdService.execute2(map);
	}

	
}
