package com.jdu.app04.service;

import java.util.List;

import com.jdu.app04.domain.BoardDTO;

public interface BoardService {
	// 인터페이스에 기능 구현할때는 하나씩 각각 등록할것
	// 서비스 계층의 메소드명은 가급적 "사용자 친화적"으로 작성하자.
	public List<BoardDTO> getBoardList(); 		//다수를 반환
	public BoardDTO	getBoardByNo(int board_no);	// 하나를 반환
	public int addBoard(BoardDTO board);
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int board_no);
	// 삽입 수정 삭제 같은 경우는 숫자를 반환해주면 된다

}
