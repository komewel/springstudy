package com.jdu.app04.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdu.app04.domain.BoardDTO;
import com.jdu.app04.repository.BoardDAO;

/*
 @Component
 1. rootcontext가서 스프링 컨테이너에 저장하지 말고
    BoardServiceImpl 클래스 타입의 객체를 만들어서 스프링 컨테이너에 저장하시오.(=@Component)
 2. <bean>태그나 @Configuration + @Bean 애너테이션을 대체하는 방식이다.
 3. Layer별 전용 @Component가 만들어져 있다.
 	1) 컨트롤러 	  : @Controller
 	2) 서비스 		  : @Service
 	3) 레파지토리영역 : @Repository
 	
 @Component(=Bean), 스프링 컨테이너의 만들어진 Bean이다, 이와같이 만들면 @configuration이나 @bean으로 사용할일이 없다 허나 한가지 단점이 @Autowired를 통해서 인식되려면 Component-Scan이 (같이)등록되어 있어야 한다.
	Component-Scan 등록하는법 
	1. <context:component-scan>이라는 태그쓰기 (servlet-context에 이미 되어있기 때문에 따로 설정해줄 필요가 없다, 심지어 경로까지 지가 아라서 등록되어있다.)
	2. @ComponentScan이라는 애너테이션 쓰기 	
 */

@Service // @Component 라고 해도되는데 @Service에서 구현할수 있는 기능을 다 못쓴다, 객체로 만들어졌으니 @autowired로 가져다 쓰는게 가능해진다.
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boradDAO;
	
	@Override
	public List<BoardDTO> getBoardList() {
		
		return null;
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		
		return null;
	}

	@Override
	public int addBoard(BoardDTO board) {
		
		return 0;
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		
		return 0;
	}

	@Override
	public int removeBoard(int board_no) {
		
		return 0;
	}

}
