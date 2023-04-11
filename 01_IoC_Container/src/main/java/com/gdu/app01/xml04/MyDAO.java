package com.gdu.app01.xml04;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyDAO {
	
	// field
	// 지금은 db를 이용할건 아니고 connection을 어떻게 이용할건지만 볼거다
	private Connection con;
	
	// singleton pattern - app-context.xml에서 <bean> 태그를 만들 때 사용된다. 
	
	// method
	public Connection getConnection() {
		
		// Spring Container에 만들어 둔 myConn Bean 가져오기
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/app-context.xml");
		con = ctx.getBean("myConn", MyConnection.class).getConnection(); // MyConnection 객체인데 getConnection을 붙이면 완성
		
		ctx.close();
		return con;
	}
	
	public void close() {
		try {
			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void list() {
		
		con = getConnection();
		
		close();
		
	}
	
}
