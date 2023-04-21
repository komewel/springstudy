package com.gdu.app07.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;

@Repository
public class BoardDAO { // DAO는 뭔가 만드는게 아니라 그냥 값만 넘겨주는 역할 중간 다리 역할 , 서비스가 전달해주는 데이터를 받아서 쿼리문에 준다
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;  // 매퍼와 연결된 메소드를 불러올수 있다, 사실상 얘가 모든 처리를 다 해준다, 매퍼랑 연결되야지만 cp가 가능하다 
	
	private final String NS = "mybatis.mapper.board.";
	
	// 1. 목록
	public List<BoardDTO> selectBoardlist() { // 메소드명은 쿼리문에 id로 하기, 인수 설정도 딱히 필요없다 줄게 없으므로  
		return sqlSessionTemplate.selectList(NS + "selectBoardList"); // mapper의 네임스페이스와 뒤에 아이디를 붙여서 (쿼리 실행을 위해).
	}	// 매퍼한테 list를 받아왔다, 서비스한테 돌려준다
	 
	// 2. 상세
	public BoardDTO selectBoardByNo(int boardNo) {
		return sqlSessionTemplate.selectOne(NS +"selectBoardByNo", boardNo); // 인수값이 하나이기 때문에 selectOne, 두번째 값은 넘기는 값
	}
	
	// 3. 삽입
	public int insertBoard(BoardDTO board) {  // 매퍼가 제공하는거 그대로 설정한다 쿼리가 뭐 주는지 보고 결정하면 된다
		return sqlSessionTemplate.insert(NS + "insertBoard", board); // 서비스 구현 할때 이값이 제대로 들어갔나 확인해본다 아니면 nullpointException발생
	}
	
	// 4. 수정
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update(NS + "updateBoard", board);
	}
	
	// 5. 삭제
	public int deleteBoard(int boardNo) {
		return sqlSessionTemplate.delete(NS + "deleteBoard", boardNo);
	}

}
