package com.jdu.app04.controller; // 서비스를 가져다 쓰는건 controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdu.app04.service.BoardService;

@RequestMapping("/board") // /board/list.do앞에 중복되는 /board같은건 컨트롤러 위에 설정해주면 된다.(중간매핑)
@Controller				  // 모든 mapping에 /board가 prefix로 추가됩니다.(별도의 컨트롤러로 빼준거다.
public class BoardController {
	@Autowired private BoardService boardService; // 컨트롤러에 @Service로 bean화 시켰기때문에 기능을 쓸수 있어진다.
	
	@GetMapping("/list.do") // 고로  /board/list.do 같은 형식을 /list.do로 간소화 가능, 이렇게 하면 프로젝트를 팀으로 해도 충돌없이 꼬이는것 없이 작업 수행이 쉬워진다.
	public String list(Model model) { // Model : jsp로 전달(forward) 할 데이터(속성, attribute)를 저장한다, model을 이용하면 실제로는 request에 저장된다.
		
		return "board/list";
	}
	
	
}
