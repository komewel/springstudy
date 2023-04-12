package com.gdu.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
	@Controller
	안녕. 난 컨트롤러야
	@Component를 포함하고 있어서 자동으로 Spring Container에 Bean으로 등록되지
	나는 스프링에 의해서 사용되고 있어.
 */
@Controller
public class MyController {
	
	
	// 메소드 : 요청과 응답을 처리하는 단위
	
	/*
	 	메소드 작성 방법
	 	1. 반환타입 : String (응답할 jsp의 이름을 작성한다.)
	 	2. 메소드명 : 아무 일도 안 합니다.(서로 중복만 안되면 된다.)
	 	3. 매개변수 : 요청에 응답에 따라 다르다. (요청이 필요한 경우 HttpServletRequest, 응답이 필요하면 HttpServletResponse 등)
	 	아무것도 필요없을땐 그냥 비워두면 된다(메소드 인수안에)
	 */
	
	/*
	 	@RequestMapping
	 	1. value : URL Mapping을 지정한다. (동작할 주소를 작성한다.) 이 주소가 오면 동작해주세요
	 	2. method : Request Method를 작성한다. (GET, POST, PUT, DELETE 등)
	 */
	/*
	 	@RequestMapping(value="/", method=RequestMethod.GET)
	 	URL Mapping이 "/"이면 context path 경로를 의미한다 (http://localhost:9090/app02)
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index"; // viewResolver에 의해서 해석된다.(servlet-context.xml을 참고하자.)
						// /WEB-INF/views/index.jsp 뷰 리졸버에 의해서 이렇게 해석된다.
	}
	
	/*
		@RequestMapping 작성 예시
		@RequestMapping(value="/list.do", method=RequestMethod.GET)
		@RequestMapping(value="list.do", method=RequestMethod.GET)  value는 슬래시(/)로 시작하지 않아도 된다 굳이 안지켜도 된다 HandlerMapping이 알아서 찾아준다.
		@RequestMapping(value="/list.do") method를 빼먹어도 GET방식의 method는 생략 가능하다.
		@RequestMapping("/list.do") value 속성만 작업하는 경우도 가능하다
		@RequestMapping("list.do") 가장 축소 버전으로 이렇게도 가능하다.
	 */
	
	@RequestMapping("/list.do") // a태그로 경로를 줬으니 GET 방식이다.
	// @WebServlat("*.do") 이걸 안하는 이유는 dispatcherServlet을 거쳐서 HandlerMapping 알아서 이경로에 매핑이 있군요 하면서 알아서 작업을 해준다 
	public String list() {
		return "board/list"; // 실제 처리되는 경로 : /WEB-INF/views/board/list.jsp
		/*
		 	return "/board/list"; 으로 하면
		 	실제 처리되는경로: /WEB-INF/views//board/list.jsp
		 	하지만 실제로는 슬래쉬 두개는 무시되고 하나로 처리된다, /WEB-INF/views/board/list.jsp 경로로 처리된다.
			매핑은 슬래쉬로 시작하고 경로는 슬래쉬로 시작안하고 이게 정식버전이다.
		 */
	}
	
	
}
