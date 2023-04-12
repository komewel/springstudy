package com.gdu.app01.xml03;
// Namespaces 안에 p기능을 부여 해주면 따로 property 태그를 안줘도 각각 필드값에 value값이나 객체화 시켜줄 수 있다.
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml03/app-context.xml");
		
		Board board = ctx.getBean("board", Board.class);
		System.out.println("제목: " + board.getTitle());
		System.out.println("내용: " + board.getContent());
		System.out.println("작성자ID: " + board.getWriter().getId());
		System.out.println("작성자명: " + board.getWriter().getName());
		
		ctx.close();
	}

}
