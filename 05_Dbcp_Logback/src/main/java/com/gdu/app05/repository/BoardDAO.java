package com.gdu.app05.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gdu.app05.domain.BoardDTO;

// @Repository 대신 Appconfig에 @Bean이 등록되어 있다.
public class BoardDAO {

	// dbcp 방식 (jdbc + DataSource), 4장보다 성능은 좋아졌지만 단위테스트가 안된다는 단점이 있다. 
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private DataSource datasource;
	
	// BoardDAO 생성자(webapp/META-INF/context.xml에 작성한 <Resource> 태그읽기, db 접속 정보가 담겨 있기 때문에)
 	public BoardDAO() {
		// JNDI 방식 : <Resource> 태그의 name 속성으로 Resource를 읽어 들이는 방식
 		try {
 			Context context = new InitialContext(); // name으로 읽어들이면 이 결과물은 datasource이다
 			datasource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61"); // webapp에서 META-INF에서 context.xml에서 name 값을 참조한것이다, connection을 8개 만들라고 시켰다 고로 8개를 가지고 있다, 허나 tomcat이 "java:comp/env/jdbc/GDJ61"이 읽어줘야 하는데 못 읽어줘서 단위테스트 안됨!!!!
 		}catch (Exception e) {
 			e.printStackTrace();
 		}
	}
	
	// private 메소드 - 1 (BoardDAO 클래스 내부에서만 사용
	public Connection getConnection() { //커넥션을 반환하는 
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // ojdbc8.jar 메모리 로드
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// private 메소드 - 2
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close(); // 사용한 Connection을 dataSource에게 반납한다.
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	// DAO 메소드 (BoardServiceImpl 클래스에서 사용하는 메소드, 서비스는 컨트롤러가 서비스는 DAO를 갖다가쓴다.)
	// DAO 메소드명 (둘다 선택일뿐 강제는없다)
	// 방법1. ServiceImpl의 메소드와 이름을 맞춤(실무에서는 이경우로 많음)
	// 방법2. DB친화적으로 새 이름을 부여(이론상 권장)
	
	// 1. 목록
	public List<BoardDTO> SelectBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = datasource.getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	// 2. 상세
	public BoardDTO selectBoardByNo(int board_no) {
		BoardDTO board = null;
		try {
			con = datasource.getConnection(); //datasource가 관리하는 Connection 8개 중 하나를 대여한다.
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?"; // + board_no 이런식으로 넣으면 보안상 안좋다
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no); // ?, 1번에 board_no로 채우기
			rs = ps .executeQuery(); // 쿼리문을 실행해달라
			if(rs.next()) { // 행이 존재하면 true 존재 하지 않으면 false 
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return board;
	}
	
	
	
	// 3. 삽입
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = datasource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	
	
	// 4. 수정
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = datasource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	
	
	// 5. 삭제
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = datasource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
}

