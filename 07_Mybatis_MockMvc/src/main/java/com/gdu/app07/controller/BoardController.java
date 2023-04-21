package com.gdu.app07.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app07.service.BoardService;
import com.gdu.app07.service.BoardServiceImpl;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	/*
	  데이터(속성) 저장 방법
	  1. forward  : Model에 attribute로 저장한다.
	  2. redirect : RedirectAttributes에 flashAttribute로 저장한다, 리다이렉트 할때 속성 저장하는 용도
	 */
	
	@GetMapping("/list.do")
	// getBoardList() 서비스가 반환한 List<BoardDTO>를 /WEB-INF/views/board/list.jsp로 전달한다, 이게바로 list.do가 할 일이다. 
	public String list(Model model) {  // 반환타입은 jsp의 이름이므로 string이다. // forward를 할거면 model에다가 저장해야한다.
		model.addAttribute("boardList", boardService.getBoardList()); // 이렇게만 하면 알아서 저장이 된다.
		return "board/list";
	}
	
	// 호랑이 담배피던 시절 ModelAndView 클래스, 사장된 기술이다
	/*
	@GetMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardService.getBoardList());
		mav.setViewName("board/list");
		return mav;
	}
	*/
	
	// getBoardByNo() 서비스가 반환한 BoardDTO를 /WEB-INF/views/board/detail.jsp로 전달한다.
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(request));
		return "board/detail";
	}
	
	@GetMapping("/write.do") 
	public String write() {
		return "board/write";
	}
	
	//addBoard() 서비스가 반환한 0 또는 1을 가지고 /board/list.do으로 이동(redirect)한다, 그전에는 리다이렉트는 list.do로만 보냈지 list에서 포워드 하는것까지 연결하는건 불가능했는데 두단계가 한번에 된다는 것 같은데
	//addBoard() 서비스가 반환한 0 또는 1을 가지고 /WEB-INF/views/board/list.jsp에서 확인한다.
	@PostMapping("/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) { // model대신에 선언된거라 보면된다.
		redirectAttributes.addFlashAttribute("addResult", boardService.addBoard(request)); // addFlashAttribute랑 addAttribute랑 차이는 전자는 두단계 후자는 한단계만 보내준다 후자는 마치 model처럼 작동한다.
		return "redirect:/board/list.do";  												   // redirectAttributes에 저장된값을 jsp에서 el태그로 이용할 수 있다.
	}
	 
	//addBoard() 서비스가 반환한 0 또는 1을 가지고 /board/detail.do으로 이동(redirect)한다
	//addBoard() 서비스가 반환한 0 또는 1을 가지고 /WEB-INF/views/board/detail.jsp에서 확인한다.
	@PostMapping("/modify.do") // 
	public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modifyResult", boardService.modifyBoard(request));
		return "redirect:/board/detail.do?boardNo=" + request.getParameter("boardNo"); 
		// 파라미터값을 안넘겨주면 null로 인식해줘서 오류는 안나지만 틀렸다, 파라미터값을 따로 넘겨줘야 한다.
	}
	
	// removeBoard() 서비스가 반환한 0 또는 1을 가지고 /board/list.do으로 이동(redirect)한다.
	// removeBoard() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/list.jsp에서 확인한다.
	@PostMapping("/remove.do")
	public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", boardService.removeBoard(request));
		return "redirect:/board/list.do";
	}
	
	// 트랜잭션 테스트
	@GetMapping("/tx.do") // 주소를 이용한 실험을 하기위해
	public void tx() {
		boardService.testTX();
	}
}
