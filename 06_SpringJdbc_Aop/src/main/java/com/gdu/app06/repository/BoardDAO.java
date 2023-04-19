package com.gdu.app06.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate; // Connection, PreparedStatement, ResultSet을 사용하는 스프링 클래스
	private String sql;

	// 목록
	public List<BoardDTO> SelectBoardList() {
		sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
		List<BoardDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class)); // selectList 돌릴때 쓰는 query라는 메소드가 있다,BeanPropertyRowMapper<>(BoardDTO.class), 알아서 행단위로 가지고 온 데이터를 bean데이터화 시켜준다. 
		return list;
		/* try {
			con = getConnection(); // connection을 빌려온거고 
			ps = con.prepareStatement(sql); // con에다가 위에 적혀있는 실행시키기 위해 바구니에 담는거 같다.
			rs = ps.executeQuery(); // 쿼리문을 실행시키라는 메소드 같고
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(); // 이거는 위에 내가 만들어둔 메소드 그대로 이용한다
		}
		return list; // 그럼 이걸 DTO에 저장시킨다는건가 그걸 우리는 불러서 화면 구성을 하는거고
		*/
	}
	
	// 상세
	public BoardDTO selectBoardByNo(final int BOARD_NO) {  // 매개변수의 변조를 막기 위해서 fianl 처리하므로 대문자로 바뀐다.(예전에 매개변수 해킹 시도가 있었다.)
		sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?"; // 원하는 카럼 BOARD_NO를 알려주면 골라서 보여준다
		BoardDTO board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), BOARD_NO);  // queryForObject, 결과가 하나일때 쓰는 메소드, 마지막은 전달하는 파라미터
		return board; 
	}
	
	// 삽입
	public int insertBoard(final BoardDTO BOARD) {
		sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() { // 트랜젝션은 모두 update라는 메소드를 쓴다. 
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARD.getTitle());
				ps.setString(2, BOARD.getContent());
				ps.setString(3, BOARD.getWriter());
			}
		});
		return result;
		/*
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle()); // dto는 딱 중간다리 역할이구나
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result; // 흐름상 rs는 boolean값을 반환하니 int값을 반환하려고 이렇게 설정해둔거 같은데, 전에 1이면 업데이트하고 0이면 하라고 한말이 있었던거 같은데
		*/
	}

	// 수정
	public int updateBoard(final BoardDTO BOARD) {
		sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() { // 트랜젝션은 모두 update라는 메소드를 쓴다. 
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARD.getTitle());
				ps.setString(2, BOARD.getContent());
				ps.setInt(3, BOARD.getBoard_no());
			}
		});
		return result;
	}
	
	// 삭제
	public int deleteBoard(final int BOARD_NO) {
		sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() { // 트랜젝션은 모두 update라는 메소드를 쓴다. 
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, BOARD_NO);
			}
		});
		return result;
	}
}