package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@configuration
	안녕 난 Configuration이라고 해.
	Spring Container에 Bean을 만들어 두는 Java 클래스야.
	(다른일은 안한다.)
	Spring Bean Configuration File 하고 같은 일을 하지
*/
@Configuration // bean을 만드는 역할을 하는 클래스이다.
public class AppContext {

// Bean을 만들고 싶으면 메소드를 만들면 됩니다. (Bean 하나 = 메소드 하나)	
/*
	@Bean
	안녕 난 Bean을 만드는 메소드야.
	반환타입이 Bean의 타입(<bean> 태그의 class 속성)이고,
	메소드명이 Bean의 이름(<bean> 태그의 id 속성)이야.
*/
@Bean // Bean은 constructor와 setter 조합으로 만드는데 이거랑 전혀 차이가 없다.
public Contact contact1() {	   // <bean id="contact1" class="Contact" > 이거랑 같다.
	Contact c = new Contact(); // default constructor
	c.setTel("02-2222-2220");  // setter  <property name="tel" value="02-2222-2220" />
	c.setFax("02-2222-2229");  // setter  <property name="fax" value="02-2222-2229" />
	return c;				   // 반환한 객체 c가 Spring Container에 저장됩니다.
							   // 원래 방식이랑 이방식 둘중에 쓰는걸 선택해야 한다 근데 보통의 입장에서는 
							   // 이방식처럼 java로 코드 짜는게 쉬울것 같다 새로만드는 영역에선 자바
							   // 운영에선 xml이 더 편하다.
}

@Bean
public User user1() {		   // <bean id="user1" class="User">
	User u = new User();       // default constructor
	u.setId("spider");	   // setter <property name="id" value="spider" />
	u.setContact(contact1());  // setter <property name="contact" ref="contact1" />
	return u;				   // 반환한 객체 u가 Spring Container에 저장됩니다.
	}

@Bean(name="contact2") 	// name 속성이 추가되면 Bean의 id로 사용된다
public Contact www() {  // name 속성이 사용되었으므로 메소드명은 더 이상 Bean의 id가 아니에요. 아무이름이나 써도 됩니다
	return new Contact("02-333-3333", "02-333-3334");
}

@Bean(name="user2") 	// name 속성이 추가되면 Bean의 id로 사용된다
public User user2() {	// name 속성이 사용되었으므로 메소드명은 더 이상 Bean의 id가 아니에요. 아무이름이나 써도 됩니다
	return new User("superman", www());
}

@Bean
public Contact contact3() {
	Contact a = new Contact();
	a.setTel("010-5555-5555");
	a.setFax("010-2222-2222");
	return a;
}

@Bean
public User user3() {
	User b = new User();
	b.setId("kyh");
	b.setContact(contact3());
	return b;
}

@Bean(name="contact4")
public Contact aaa() {
	return new Contact("010-6544-8879", "020-6544-7789");
}

@Bean(name="user4")
public User bbb() {
	return new User("fff", aaa());
}
}