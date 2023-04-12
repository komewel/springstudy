package com.gdu.app01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		// property태그안에 value태그로 값을 주고 ref태그 속성 bean으로 객체화 시켜준다.

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml02/app-context.xml");
		// 딱히 중요한 코드는 아니라고 함
		
		Academy aaa = ctx.getBean("academy", Academy.class);
		
		System.out.println("이름: " + aaa.getName());
		System.out.println("도로명주소: " + aaa.getAddress().getDaddr());
		System.out.println("지번주소: " + aaa.getAddress().getGaddr());
		System.out.println("팩스번호: " + aaa.getAddress().getContact().getFaxNumber());
		System.out.println("전화번호: " + aaa.getAddress().getContact().getPhoneNumber());
		
		ctx.close();

		

		}

}
