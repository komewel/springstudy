package com.gdu.app05.service;

import java.util.List;

import com.gdu.app05.domain.BoardDTO;

public interface BoardService {

	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(int board_no);
	public int addBoard(BoardDTO board); // 여러정보를 반납해야하니 객체로 전달받아야 한다
	public int modifyBoard(BoardDTO board); // 이하동문
	public int removeBoard(int board_no);
	
}
