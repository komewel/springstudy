package com.gdu.app01.java_into_xml;
// 자바로 bean 파일 작업하고 보통 annotation기능을 쓰는데 
// generic을 쓴다 xml에서 빈 기능을 쓰면서 이렇게 되는듯
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		// java_into_xml 폴더에 있는 app-context.xml에 정의된 Bean을 주세요!
		// GenericXmlApplicationContext 이기능은 xml 파일에 대해 쓰는 기능이다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("java_into_xml/app-context.xml");
		
		// 이름이 book인 Bean을 주세요!
		Book book = ctx.getBean("book", Book.class);
		
		System.out.println("제목: " + book.getTitle());
		System.out.println("출판사명: " + book.getPublisher().getName());
		System.out.println("출판사전화: " + book.getPublisher().getTel());
		ctx.close();
	}

}
