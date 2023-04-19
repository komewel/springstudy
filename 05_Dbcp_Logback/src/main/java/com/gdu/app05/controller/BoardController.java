package com.gdu.app05.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	
	// BoardController 클래스를 실행할 때 org.slf4j.Logger를 동작시킨다.
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/list.me")
	public String list(Model model) {
		List<BoardDTO> list = boardService.getBoardList();
		LOGGER.debug(list.toString());  // 목록 결과 확인
		model.addAttribute("boardList", list);
		return "board/list";
	}
	
	@GetMapping("/write.me") // 따로 전해줄값이 필요하지 않나보다
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.me")
	public String add(BoardDTO board) { // 추가하려면 정보가 한두개가 아니므로 
		LOGGER.debug(board.toString()); // 파라미터 확인
		LOGGER.debug(boardService.addBoard(board) + ""); // 결과 확인
		return "redirect:/board/list.me";
	}
	
	@GetMapping("/detail.me")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no,
						 Model model) {
		LOGGER.debug(board_no + ""); // 파라미터 확인
		BoardDTO b = boardService.getBoardByNo(board_no);
		LOGGER.debug(b.toString()); // 상세 결과 확인
		model.addAttribute("b", b);
		return "board/detail";
	}
	
	@GetMapping("/remove.me")
	public String remove(@RequestParam(value="board_no", required=false, defaultValue="0")int board_no) {
		LOGGER.debug(board_no + ""); // 파라미터 확인
		LOGGER.debug(boardService.removeBoard(board_no) + ""); // 결과 확인
		return "redirect:/board/list.me";
	}
	
	@PostMapping("/modify.me")
	public String modify(BoardDTO board) {
		LOGGER.debug(board.toString());  // 파라미터 확인
		LOGGER.debug(boardService.modifyBoard(board) + "");  // 결과 확인
		return "redirect:/board/detail.me?board_no=" + board.getBoard_no();
	}
	}
