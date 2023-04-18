package com.gdu.app05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app05.repository.BoardDAO;
import com.gdu.app05.service.BoardService;
import com.gdu.app05.service.BoardServiceImpl;

@Configuration
public class AppConfig {

	@Bean // BoardServiceImpl @service를 표현하는 다른 방식
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean // BoardDAO @Repository를 표현하는 다른 방식
	public BoardDAO boardDAO() {
		return new BoardDAO();
	}
}
