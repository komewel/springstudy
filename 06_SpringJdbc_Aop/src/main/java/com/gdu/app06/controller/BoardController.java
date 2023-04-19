package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// ParameterCheckAOP에 의해서 파라미터 체크할 애들은 메소드 이름을 모두 ParamCheck로 끝나게 설정했다
	
	@GetMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}

	@PostMapping("/add.do")
	public String addParamCheck(BoardDTO board) {
		boardService.addBoard(board);
		return "redirect:/board/list.do"; // 목록보기로 가기위해 리다이렉트를 한다
	}
	
	@GetMapping("/detail.do")
	public String detailParamCheck(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(board_no)); // 서비스한테 정보를 넘겨주고 b에 저장해서 detail에 넘겨주는구나 
		return "board/detail";
	}

	@GetMapping("/remove.do")
	public String removeParamCheck(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no) {
		boardService.removeBoard(board_no);
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/modify.do")
	public String modifyParamCheck(BoardDTO board) {
		boardService.modifyBoard(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no(); 
	}
	
	// 트랜잭션 처리 확인을 위한 testTx() 메소드 호출하기
	@GetMapping("/tx.do") // http://localhost:9090/app06/board/tx.do 완성본
	public String tx() {
		boardService.testTx();
		return "redirect:/board/list.do";
		
	}
}