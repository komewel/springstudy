package com.gdu.app08.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app08.domain.BoardDTO;

// 원래는 DAO가 mapper를 참고하는 방식으로 이용했는데 mapper를 인터페이스형식으로 사용한다. DAO가 인터페이스로 mapper가 IMPL같이

/*
	 	@Mapper
	 	
	 	1. Mybatis의 mapper의 쿼리문을 직접 호출할수 있는 인터페이스이다.
	 	2. 쿼리문의 id와 메소드명을 동일하게 처리한다.
	 	3. DBconfig.java(SqlSessionTemplate Bean이 정의된 파일)에 @MapperScan을 추가해야한다.
*/
@Mapper // mybatis에서 지원하는 기능이다, @Repository를 안쓴다, 추상메소드명을 xml의 id하고 맞추면 된다, 실제로는 board.xml을 부르는거고 DAO가 없어지고 mapper를 바로 부르는 방식이라고 생각해도된다.
public interface BoardMapper {
	public List<BoardDTO> selectBoardList();
	public BoardDTO selectBoardByNo(int boardNo);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int boardNo);
	public int deleteBoardList(List<String> boardNoList);
	public int selectBoardCount();
}
