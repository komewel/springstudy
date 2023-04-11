package com.gdu.app01.java01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		/*
		 	@Configuration, @Bean 애너테이션으로 생성한 Bean을 가져올 때 사용하는 스프링 클래스
		 	AnnotationConfigApplicationContext
		 */
		// AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class); // AppContext.java 파일에 있는 Bean을 주세요!!
		// @Configuration 붙여 놓은게 하나다 하면 클래스이름 적어주는 방법을 쓴다(2번째)
		// 아님 두개이상이다 하면 한번에 패키지파일을 다 뒤져라 라는 방식에 메소드를 쓸수있다(4번째)
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.gdu.app01.java01"); // com.gdu.app01.java01 패키지에 있는 모든 Bean을 주세요!!
		// 둘 이상의 패키지를 지정할때는 ({"com.gdu.app01.java01", ...})이런식으로 복수 지정 가능하다
		
		User user1 = ctx.getBean("user1", User.class);
		System.out.println(user1.getId());
		System.out.println(user1.getContact().getTel());
		System.out.println(user1.getContact().getFax());
		System.out.println();
		
		User user2 = ctx.getBean("user2", User.class); // 클래스의 메소드명과 변수명이 같아야 한다
		System.out.println(user2.getId());
		System.out.println(user2.getContact().getTel());
		System.out.println(user2.getContact().getFax());
		System.out.println();
		
		User user3 = ctx.getBean("user3", User.class);
		System.out.println(user3.getId());
		System.out.println(user3.getContact().getTel());
		System.out.println(user3.getContact().getFax());
		System.out.println();
		
		User user4 = ctx.getBean("user4", User.class);
		System.out.println(user4.getId());
		System.out.println(user4.getContact().getTel());
		System.out.println(user4.getContact().getFax());
		
		ctx.close();
		
	}
	
		

}
