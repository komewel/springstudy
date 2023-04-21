package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.app07.domain.BoardDTO;

@Service
public interface BoardService { // 서비스는 다오로부터 값을 받아오고 또는 준다, 서비스는 로직을 짜는곳이다.
	
	public List<BoardDTO> getBoardList(); // 다오로부터 받은 데이터를 그대로 컨트롤러에 넘긴다, 컨트롤러는 jsp로부터 받는다
	public BoardDTO getBoardByNo(HttpServletRequest request);
	public int addBoard(HttpServletRequest request);
	public int modifyBoard(HttpServletRequest request);
	public int removeBoard(HttpServletRequest request);
	// jsp로부터 파라미터가 넘어오면 컨트롤러는 HttpServletRequest request로 받겠다 그럼 서비스 한테 넘겨만주겠다
	public void testTX();
	
}
