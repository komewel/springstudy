package com.gdu.app01.xml04;
// dao를 간편하게 만들 수 있다 덩달아 싱글톤도 가볍게 만드는거 보고 놀랬다
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/app-context.xml");
		MyDAO dao = ctx.getBean("dao", MyDAO.class);
		dao.list();
		
		ctx.close();
	}

}
