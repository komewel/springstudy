package com.jdu.app04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jdu.app04.domain.BoardDTO;
import com.jdu.app04.repository.BoardDAO;

// Junit4
@RunWith(SpringJUnit4ClassRunner.class)

/*
	  @ContextConfiguration
	  1. 어떤 방식으로 Spring Container에 Bean을 등록했는지 알려주는 역할이다.
	  2. Bean 등록 방식 3가지 (@Component, bean태그, @Configuration)
	  	1) root-context.xml에 <bean>태그를 추가한 경우
	  		@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
	  		
	  	2) com.gdu.app04.config.AppConfig.java에 @Configuration + @Bean 을 작성한 경우
	  		@ContextConfiguration(classes={AppConfig.class})
	  	
	  	3) @Component(@Controller, @Service, @Repository)를 작성한 경우
	  	 	@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
 */
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // 제대로 작성 안해주면 null값이 나온다
/*
	 테스트 순서 결정하기
 	1. 순서 : 삽입 - 상세 - 수정 - 상세 - 목록 - 삭제 - 목록
 	2. 테스트 메소드 명을 기준으로 순서를 결정하는 @FixMethodOrder 사용
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 이름의 오름차순 (메소드이름의),메소드명의 알파벳순으로 테스트를 수행한다.
public class BoardUnitTest {
	
	// DAO 메소드 단위로  Unit 테스트를 진행하기 위해서  BoardDAO 타입의 Bean이 필요하다.
	@Autowired
	private BoardDAO boardDAO;
	
	// BoardUnitTest 클래스를 실행할 때  org.slf4j.Logger를 동작시킨다.
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardUnitTest.class);
	
	// 테스트 메소드
	@Test
	public void a삽입테스트() {
		BoardDTO board = new BoardDTO(0, "제목", "내용", "작성자", null, null);
		assertEquals(1, boardDAO.insertBoard(board)); // boardDAO.insertBoard(board) 결과가 1이면 테스트 성공!!!
	}
	
	@Test
	public void a2상세테스트() {
		int board_no = 1;
		BoardDTO board = boardDAO.selectBoardByNo(board_no);
		//System.out.println("a2 - " + board); // LOG기능으로 대체한 예정
		LOGGER.info(board.toString());  // LOG 기능으로 대체 완료
		assertNotNull(board); // board가 null이 아니면 테스트 성공!
	}
	
	@Test
	public void a3수정테스트() {
		BoardDTO board = new BoardDTO(1, "[수정]제목", "[수정]내용", null, null, null);
		assertEquals(1, boardDAO.updateBoard(board));
	}
	
	@Test
	public void a4상세테스트() {
		int board_no = 1;
		BoardDTO board = boardDAO.selectBoardByNo(board_no);
		//System.out.println("a4 - " + board); // LOG기능으로 대체할 예정, 웹개발 하면서 system.out 남겨둘 이유가 없음, 생각보다 기능을 많이 잡아먹는 기능이기 때문에 남발하면 혼남, 대신 log를 쓰자 pom에 추가한 기능이 있다 
		LOGGER.info(board.toString());  // LOG 기능으로 대체 완료
		assertNotNull(board); // board가 null이 아니면 테스트 성공!
	
	}
	
	@Test 
	public void a5목록테스트() {
		List<BoardDTO> list = boardDAO.SelectBoardList();
		// System.out.println("a5 - " + list);
		LOGGER.info(list.toString()); // log 찍는기능으로 대체 완료
		assertEquals(1,  list.size()); // 목록 개수가 1개이면 테스트 성공!!
	}

	@Test
	public void a6삭제테스트() {
		int board_no = 1; // 삭제할 게시글 번호
		assertEquals(1, boardDAO.deleteBoard(board_no)); // boardDAO.deleteBoard(board) 결과가 1이면 테스트 성공!!!
	}
}

