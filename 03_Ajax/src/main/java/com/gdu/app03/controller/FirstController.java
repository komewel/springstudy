package com.gdu.app03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.Person;
import com.gdu.app03.service.IFirstService;

@Controller
public class FirstController {

	// FirstServiceImpl을 Spring Container에 Bean을 등록시켜보자(root-context.xml에 <bean>태그등록, 그래야지 @Autowired를 쓸수 있음)
	// Spring Container에 등록된 firstService Bean을 아래 필드 firstService에 주입해보자 (필드에 @Autowired 추가)
	
	@Autowired // 스프링이 알아서 객체화 시켜준다.
	private IFirstService firstService;
	
	@ResponseBody // 응답을 해주는 새로운 애너테이션 등장, 이걸 붙여줘야지 메소드의 반환값을 jsp 파일명으로 해석하지 않는다, 그냥 응답하는값(데이터, 요청하는곳으로 보내주는 데이터)이다	
	@GetMapping(value="/first/ajax1", produces="application/json") // 응답데이터의 타입을 적어줄수 있다 , 로 두번째 속성 기입 가능(produces: 응답할 데이터의 MIME TYPE)
	public Person ajax1(HttpServletRequest request,  //그렇지만 반환타입이 Person인데 이게 json이냐? 아닌데? 응답 데이터(반환값 Person 객체를 자동으로 JSON데이터로 변환해준다 "Jackson 라이브러리"가!! produces"application/json"를 참고해서 알아서 해준다.)
						HttpServletResponse response) {							   // 메이븐 레파지토리가서 JacksonDatabind 2.14.2 적용 땡큐땡큐!!
		return firstService.execute1(request, response);	// 서비스가 만들어준 Person 객체를 
			// 값을 넘겨준다.
			// 이걸 jsp 파일명으로 본다고 하면 말이 안된다. ajax는 페이지이동 없이 값만 받아와서
			// 화면에 뿌려준다 이래서 새로운 애너테이션이 등장한다.
	}
	
	@ResponseBody
	@GetMapping(value="/first/ajax2", produces="application/json")
	public Map<String, Object> ajax2(@RequestParam("name") String name, @RequestParam("age") int age) { // 빈문자열이 왔을때 대체할거를 안만들었기 때문에 여기서 오류가 난다.
		return firstService.execute2(name, age); // 여기서도 jackson이 알아서 해준다.
		
	}
	
	@ResponseBody
	@GetMapping(value="/first/ajax3", produces="application/json")
	public Map<String, Object> ajax3(Person person) {
		return firstService.execute3(person);
	}
}	
