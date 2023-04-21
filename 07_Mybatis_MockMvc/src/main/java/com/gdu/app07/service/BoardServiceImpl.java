package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	// 다오한테 값 줄려고 이렇게 호출한거 구나
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.selectBoardlist(); // 컨트롤러로 arraylist가 반환된다.
	}

	@Override
	public BoardDTO getBoardByNo(HttpServletRequest request) {
		// 파라미터 boardNo가 없으면(null, "") 0을 사용하면 , 공백이랑 null은 느낌이 비슷하다
		// optinal은 null처리만 해준다 빈 문자열 처리는 못한다.
		String strBoardNo = request.getParameter("boardNo");
		int boardNo = 0;
		if(strBoardNo != null && strBoardNo.isEmpty() == false) { //optinal 대신에 고전방식은 이거다
			boardNo = Integer.parseInt(strBoardNo); // 원하는 값이 아니면 안넘어가게 하는것은 jsp로 잡는게 좋다(isNaN(), alert())
		}
		return boardDAO.selectBoardByNo(boardNo);
	}

	@Override
	public int addBoard(HttpServletRequest request) {
		// 나중에 오류 뜨면 ex) html 파일로 만들어서 이미지를 띄울수 있다.
		try {
			// 파라미터 title, content, writer 받아온다고 보면된다
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			// boardDAO로 전달할 boardDTO를 만들어야 한다
			BoardDTO board = new BoardDTO();
			board.setContent(content);
			board.setTitle(title);
			board.setWriter(writer);
			return boardDAO.insertBoard(board);
		}catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int modifyBoard(HttpServletRequest request) {
		try {
			// 파라미터  title, content, boardNo을 받아온다
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int boardNo = Integer.parseInt(request.getParameter("boardNo")); // 여기서 값이 진짜 안와서 오류가 난다 그러면 개발자 잘못이다.
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContent(content);
			board.setBoardNo(boardNo);
			// 여기는 모든 파라미터가 모두 post 방식인데 null이오면 0처리를 안한다 post방식에서는 주소로 편집하는 방식이 아니기 때문에, 0으로 대체하는 방식은 get에서만 가능하다.
			
			return boardDAO.updateBoard(board);
		}catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int removeBoard(HttpServletRequest request) {
		try {
			// 파라미터 boardNo를 받아온다. 
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			return boardDAO.deleteBoard(boardNo);
		}catch (Exception e) {
			return 0;  // removeResult의 0이 전달된다
		}
	}
	// get방식은 주소창으로 조작이 가능하므로 보안에 취약하다 그에 반해 post는 반대라 보안에 강하다.  
	
	// 트랜잭션 확인, aop없이도 트랜잭션이 쉽게 가능, aop를 쓰는 이유는 aop 모든 메소드에 트랜잭션을 체크 해준다, 성능은 고려 x
	@Transactional // 메소드가 실행될때 트랜잭션이 된다, 삽입, 수정, 삭제 두개 이상의 작업을 할때, 트랜잭션이 필요하면 붙여주는 애너테이션 
	@Override
	public void testTX() {
		boardDAO.insertBoard(new BoardDTO(0, "타이틀", "콘텐트", "작성자", null, null));
		boardDAO.insertBoard(new BoardDTO()); // 요거는 실패, 실패하는 이유는 타이틀 null땜에 
	}

}
