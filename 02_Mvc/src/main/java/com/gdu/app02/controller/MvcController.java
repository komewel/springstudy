package com.gdu.app02.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app02.domain.Person;

@Controller
public class MvcController {

	/*
	 	@RequestMapping을 대체하는 새로운 애너테이션(Spting4, 기본버전은 3.1.1인데 낮으면 이 애너테이션 기능을 못쓴다)
	 	1. @GetMapping, GET방식일때 사용
	 	2. @PostMapping POST방식일때 사용
	 	3. @PutMapping 수정할때 사용 ajax 쓸때 사용한다고 함
	 	4. @DeleteMapping 삭제할때 사용
	 */
	
	/*
	 	요청 파라미터의 UTF-8 인코딩 처리
	 	
	 	메소드마다 request.setCharacterEncoding("UTF-8")을 작성하는것은 매우 비효율적이므로 
	 	모든 요청(contextPath를 가진 모든 요청)마다 동작하도록 filter를 사용한다.
	 	CharacterEncodingFilter를 통해서 모든 요청마다 자동으로 UTF-8로 인코딩된다.
	 	참고할파일 : /WEB-INF/web.xml에 코드로 저장해둔다.
	 */
	
	/*
	 	1. HttpServletRequest로 요청 파라미터 처리하기
	 */
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
						// request가 있으면 요청받는거(파라미터)가 있구나 
						// model이 있으면 응답할게 있구나 포워드 할거 있구나 하고 표준화가 가능해진다.
		
		// name의 전달이 없으면 홍길동이 사용된다.
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("name"));
		String name = opt1.orElse("홍길동");
		
		// age의 전달이 없으면 "0"이 사용된다.
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("age"));
		int age = Integer.parseInt(opt2.orElse("0")); // null로 올수있을 경우를 대비해
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		// 기본 이동 방식은 forward이다.
		return "mvc/detail";  // 실제 처리 경로 : /WEB-INF/views/mvc/detail.jsp 
	
		/*
		 	참고. redirect로 이동하기
		 	return "redirect:이동할경로";
		 */
		
		/*
		 	Model model
		 	1. 스프링에서 사용하는 데이터(속성) 전달 객체이다.
		 	2. Model-2(jsp/Servlet)에서는 HttpServletRequest request 객체를 사용해서 데이터를 전달하지만,
		 	   스프링에서는 Model model 객체를 사용한다. 요청 응답 모두 request가 다 처리했었다 model-2에서는 
		 	   그치만 스프링에서는 이건 좀 아니다 싶어서 Model model 객체를 이용한다.
		 	3. forward할 데이터를 Model의 addAttribute() 메소드로 저장한다. 
		 		그렇다고 addAttribute가 포워드 전용은 아니다 오해 금지
		 		
		 	확장개념으로는 request가 더 좋다 
		 	예를 들어 request로 contextpath기능처럼 경로도 불러올수 있고 파라미터 값만 
		 	가져올수 있는게 아니라 많은 이점이 있기때문에 아예 버릴순 없다.	
		 */
	}
	
	/*
	 	2. @RequestParam으로 요청 파라미터 처리하기
	 		1) value 		: 요청 파라미터 이름
	 		2) required 	: 요청 파라미터의 필수 여부(디폴트는 true)
	 		3) defaultvalue : 요청 파라미터가 없을때 대신 사용할 값
	 			(Optional 기능을 더 쉽게 사용할 수 있다)
	 */
	@GetMapping("/detail.me") // @RequestParam 자체도 생략이 가능하다 대신 속성은 유지 안된다.
	public String detailMe(@RequestParam(value="name", required = false, defaultValue="홍길동")String name,  // 파라미터값을 찾아서 값을 넣어주겠다는거다 
						   @RequestParam(value="age", required = false, defaultValue="0" )int age,		 // 장점중에 하나가 integer.parseInt 같은건 알아서 해준다
						   Model model) {										// required false 뜻은 이 파라미터값은 필수는 아니다. defaultValue는 값이 없으면 null이면 대신해주겠다
																							
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "mvc/detail";
		
		// 목록 3번은 실행되는 이유는 요청 파라미터 name이 없으면 null이 저장된다
		// 요청 파라미터 age가 없으면  null을 int로 변환하려고 하기 때문에 오류가 벌어진다 이런 이유로 목록3번만 정상실행된다.
		// 파라미터값이 빠진게 있어서 오류가 난다 Optional이 이기능을 했는데.... defaultValue가 이 역할을 한다.
	}
	/*
	 	@GetMapping("/detail.me") 		 // @RequestParam 자체도 생략이 가능하다 
	public String detailMe(String name,  // 목록 3번은 실행되는 이유는 요청 파라미터 name이 없으면 null이 저장된다
						   int age,		 // 요청 파라미터 age가 없으면  null을 int로 변환하려고 하기 때문에 오류가 벌어진다 이런 이유로 목록3번만 정상실행된다.
						   Model model) {										
																							
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "mvc/detail";
	}
	
	파라미터값을 하나로 묶어서 관리하고 싶다 ex. 객체
	 */
	
	/*
	 	3. 커맨드 객체를 이용한 요청 파라미터 처리
	 		1) 파라미터를 필드로 가진 객체를 커맨드 객체라고 한다.(Person객체)
	 		2) setter가 사용된다.
	 		3) 커맨드 객체는 자동으로 Model에 저장된다
	 */
	
	// 객체로 파라미터를 받아서 사용하는 방법 그렇지만 커맨드 객체로 
	@GetMapping("/detail.gdu")
	public String detailGdu(Person p) {  // name과 age를 필드로 가진 커맨드 객체 Person p
										 // Model에 저장될 때 객체 이름인 p를 사용하지 않고,
										 // 객체 타입인 Person을 사용한다. (객체타입을 인식할때 소문자로 인식한다 ex. Person(x) person(x)
										 // Model에 저장되는 속성명은 객체 타입 Person을 person으로 수정해서 사용한다.
		return "mvc/detail";			 // 객체이름 p는 속성이름 만들때 사용하지를 않는다.
				
		
		// @ModelAttribute(value="p") 이기능이 있으면 person으로 인식안하고 객체명을 p로 인식해서 사용할 수 있다.(객체명과 상관없이)
		/*
		 	@GetMapping("/detail.gdu")
			public String detailGdu(@ModelAttribute(value="p") Person p) { 
			return "mvc/detail";  
			Model에 저장할 속성명을 p로 해주세요.
			${p.name}, ${p.age}와 같은 형식으로 확인할 수 있다.
			}
		 */
	}
	
}
