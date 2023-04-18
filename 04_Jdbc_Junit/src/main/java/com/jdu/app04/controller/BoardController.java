package com.jdu.app04.controller; // 서비스를 가져다 쓰는건 controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdu.app04.domain.BoardDTO;
import com.jdu.app04.service.BoardService;

@RequestMapping("/board") // /board/list.do앞에 중복되는 /board같은건 컨트롤러 위에 설정해주면 된다.(중간매핑)
@Controller				  // 모든 mapping에 /board가 prefix로 추가됩니다.(별도의 컨트롤러로 빼준거다.
public class BoardController {
	@Autowired private BoardService boardService; // 컨트롤러에 @Service로 bean화 시켰기때문에 기능을 쓸수 있어진다.
	
	@GetMapping("/list.do") // 고로  /board/list.do 같은 형식을 /list.do로 간소화 가능, 이렇게 하면 프로젝트를 팀으로 해도 충돌없이 꼬이는것 없이 작업 수행이 쉬워진다.
	public String list(Model model) { // Model : jsp로 전달(forward) 할 데이터(속성, attribute)를 저장한다, model을 이용하면 실제로는 request에 저장된다.
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	/*
	@PostMapping("/add.do")
	public String add(@RequestParam(value="title", required=false, defaultValue="홍길동") String title,
					  @RequestParam(value="writer", required=false, defaultValue="김영환") String writer,
					  @RequestParam(value="content", required=false, defaultValue="별주부전") String content,
					  Model model) {  // 파라미터 받는 3가지 방법, 통일하는 느낌으로 받고 싶다면 HttpServletRequest
		model.addAttribute("title", title);
		model.addAttribute("writer", writer);
		model.addAttribute("content", content);
		return "board/add";
	}
	*/
	
	@PostMapping("/add.do")
	public String add(BoardDTO board) {
		boardService.addBoard(board);     // addBoard() 메소드의 호출 결과인 int 값(0 또는 1)은 사용하지 않았다.
		return "redirect:/board/list.do"; // 목록보기로 리다이렉트 하겠다, 이거 다음에 적을건 매핑값을 적어야 한다, 리다이렉트 이후 경로는 항상 매핑으로 
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(board_no));
		return "board/detail";
	}

	@GetMapping("/remove.do")
	public String remove(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no) {
		boardService.removeBoard(board_no);
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/modify.do")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no(); // 바로 위에 있는 다른기능으로 넘어가기 때문에 파라미터값을 가지고 갔다. 
	}
}
