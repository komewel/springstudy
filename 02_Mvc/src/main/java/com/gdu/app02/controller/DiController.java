package com.gdu.app02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app02.domain.Bbs;

@Controller
public class DiController {

	// spring container 안에 bean 객체인 p라는 객체가 있다면 
	// @inject
	// Person p; 어딘가 선언했다 치고 그위에 주입해주십쇼 요청하면 스프링에서 container에서 만들어진 정보를 주입해준다. 
	// spring container안에 넣는 방법은 bean태그랑 @configuratuin @Bean이랑 있다.
	
	/*
	 	Spring Container에 저장되어 있는 Bean을 가져올 수 있는 Annotation
	 	
	 	1. @Inject
	 		1) Bean의 타입(class)이 일치하는 Bean을 가져와서 주입한다.
	 		2) 동일 타입의 Bean이 2개 이상이면 Bean의 이름(id)이 일치하는 Bean을 가져온다.
	 		3) 타입(class)과 이름(id)이 일치하는 Bean이 없는 경우 오류가 발생한다.
	 		4) 가져올 Bean의 이름을 강제로 지정하기 위해서 @Named(javax.inject.Named)를 사용할 수 있다.
	 			
		2. @Resource
			1) javax.annotation.Resource
			2) javax-annotation-api dependency가 필요하다.
				pom.xml에 추가할 dependency
				<dependency>
	    			<groupId>javax.annotation</groupId>
	    			<artifactId>javax.annotation-api</artifactId>
	    			<version>1.3.2</version>
				</dependency>
			3) Bean의 이름(id)이 일치하는 Bean을 가져온다.
			4) 동일한 이름의 Bean이 없으면 오류가 발생한다.
			5) 참고 예시
				@Resource(name="bbs1") private Bbs b1;
				@Resource(name="bbs2") private Bbs b2;

	 	3. @Autowired
	 		1) org.springframework.beans.factory.annotation.Autowired
			2) @Inject와 동일하게 동작한다.
				(1) Bean의 타입(class)이 일치하는 Bean을 가져온다.
				(2) 동일 타입의 Bean이 2개 이상이면 Bean의 이름(id)이 일치하는 Bean을 가져온다.
				(3) 타입(class)과 이름(id)이 일치하는 Bean이 없는 경우 오류가 발생한다.
			3) @Inject에는 없는 required 속성을 사용해서 타입(class)과 이름(id)이 일치하는 Bean이 없는 경우에 오류가 발생하지 않도록 할 수 있다.
			4) 가져올 Bean의 이름을 강제로 지정하기 위해서 @Qualifier(org.springframework.beans.factory.annotation.Qualifier)를 사용할 수 있다.
			
			
			1) Bean의 타입(class)이 일치하는 Bean을 가져와서 주입한다.(inject기반이다)
	 		2) 동일 타입의 Bean이 2개 이상이면 오류가 발생하는게 아니라 타입이 틀리면 Bean의 이름(id)도 검사한다(더블체크) 일치하는 Bean을 가져온다.
	 		3) 이걸 쓴다
	 		4) 필드가 2개이상이면 생성자를 만드는게 더 좋다
	 		5) 선언하고 getBean()대신 역할한다고 봐도 된다.
	 */
	/*
	 	@Autowired 사용 방법 3가지
	 	
	 	1. 필드에 @Autowired 선언하기
	 		1) 필드에 자동으로 Bean을 주입한다.
	 		1) 각 필드마다 @Autowired를 선언한다.
	 		2) 필드가 10개이면 @Autowired도 10번 선언해야 한다. (필드가 많은 경우 사용하지 않는다.)
	 		
	 	2. 생성자에 @Autowired 선언하기
	 		1) 생성자의 매개변수(괄호 안에 있는 변수)에 있는 객체들에 자동으로 Bean을 주입한다. 
	 		2) 생성자에는 @Autowired 선언을 생략할 수 있다.(일반적으로 생략한다.) 
	 		
	 	3. 메소드에 @Autowired 선언하기
	 		1) 메소드의 매개변수(괄호 안에 있는 변수)에 있는 객체들에 자동으로 Bean을 주입한다. 
	 		2) 메소드에는 @Autowired 선언을 생략할 수 없다.
	 */
	/*
	@Autowired 사용 예시 3가지
	
	┌─ Spring Container ─┐
	│  Bean1 : Bbs bbs1  │ - root-context.xml에서 만든 Bean
	│  Bean2 : Bbs bbs2  │ - AppConfig.java에서 만든 Bean
	└────────────────────┘
	
	
	1. 필드에 @Autowired 선언하기
	
 		**************************************
		@Autowired private Bbs bbs1;  // Bbs 타입의 Bean이 2개 있기 때문에 이름(id)이 bbs1인 Bean을 가져온다.
		@Autowired private Bbs bbs2;  // Bbs 타입의 Bean이 2개 있기 때문에 이름(id)이 bbs2인 Bean을 가져온다.
 		**************************************
 		@Autowired @Qualifier(value="bbs1") private Bbs apple;  // 강제로 Bean의 이름(id)이 bbs1인 Bean을 가져와서 apple에 전달한다.
 		@Autowired @Qualifier(value="bbs2") private Bbs mango;  // 강제로 Bean의 이름(id)이 bbs2인 Bean을 가져와서 mango에 전달한다.
 		**************************************
		

	2. 생성자 이용하기 (@Autowired 명시 없음)
	
 		**************************************
		private Bbs bbs1;
		private Bbs bbs2;
		
		public DiController(Bbs bbs1, Bbs bbs2) {  // 매개변수 Bbs bbs1과 Bbs bbs2로 Bean을 가져온다.
			this.bbs1 = bbs1;
			this.bbs2 = bbs2;
		}
 		**************************************
	
	
	3. 메소드(setter method) 이용하기
	
		**************************************
		private Bbs bbs1;
		private Bbs bbs2;
		
		@Autowired
		public void method(Bbs bbs1, Bbs bbs2) {  // 매개변수 Bbs bbs1과 Bbs bbs2로 Bean을 가져온다. 메소드명은 마음대로 지어도 된다.
			this.bbs1 = bbs1;
			this.bbs2 = bbs2;
		}
 		**************************************
 
*/
	
							// Spring Container
	private Bbs bbs1; // <bean id="bbs1" class="Bbs">
	private Bbs bbs2; // @Bean public Bbs bbs2() {}
	
	@Autowired
	public void method(Bbs bbs1, Bbs bbs2) { //아무이름이나 상관없는데 굳이 따지면 setter형식의 메소드
		this.bbs1 = bbs1;					 // 이 곳의 매개변수로 Spring Container의 Bean이 자동으로 주입된다.
		this.bbs2 = bbs2;
	}
	// ※스프링은 생성자가 있으면※ 자기가 할수 있는걸 찾아서 자동 주입한다.
	// Spring Container에서 주입할 수 있는게 있나 찾아서 자동으로 주입해준다
	// 핵심은 필드에 값이 자동으로 채워지는걸 체감할 수 있다.(스프링이 알아서 해준다!!!!가 핵심)
	
	// 메소드도 이하동문 다른점은 @Autowired 생략 불가능
	
	// 컨트롤러는 service를 호출하고 service는 DAO를 부른다.(DTO는 도구일뿐)



	@GetMapping("/bbs/detail.do")
	public String detail(Model model) {
		
		model.addAttribute("bbs1", bbs1);
		model.addAttribute("bbs2", bbs2);
		
		return "bbs/detail"; // 실체 처리 경로 : /WEB-INF/views/bbs/detail.jsp
	}
	
	
	
}
