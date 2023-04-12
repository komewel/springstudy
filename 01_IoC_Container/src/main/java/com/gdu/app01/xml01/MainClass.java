package com.gdu.app01.xml01;
// property태그에 value 속성으로 값을 준다 
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// Student haksang 객체를 만들고, haksang 객체가 가지고 있는 계산기 사용하기
		// 쓰는 클래스는 두개인데 왜 new는 하나밖에 없냐
		// 결국엔 new 없이 모든 객체 기능을 쓰는게 목표(spring의 특권)
		
		
		/*
		Student haksang = new Student();
		haksang.setCalculator(new Calculator()); // 먼저 객체화 시켜서 계산기에 인자를 넣어주겠다.
		haksang.getCalculator().add(3, 2);
		haksang.getCalculator().sub(3, 2);
		haksang.getCalculator().mul(3, 2);
		haksang.getCalculator().div(3, 2);
		*/
		
		// 쓰는 객체기능은 두개인데 new는 하나 고로 haksang객체를 이용할때 안에 set의 
		// 계산기 기능을 쓴다는 객체화 선언을 해야한다.
		// 내가 생각을 잘못한 점이 get을 써야지 기능을 불러온다 \
		
		/*
		 	<bean>태그로 생성한 Bean을 가져올 때 사용하는 스프링 클래스
		 	1. GenericXmlApplicationContext 클래스
		 	2. ClassPathApplicationContext 클래스
		 	위 클래스 중 아무거나 사용하면 된다
		 */
		
		// src/main/resources/xml01 디렉터리에 있는 app-content.xml 파일에 정의된 Bean을 쓸게요!
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml01/app-context.xml"); 
		// src/main/resources는 명시하지 않습니다.
		
		// Bean 중에서 student란 id를 가진 Bean을 주세요!
		Student haksang = ctx.getBean("student", Student.class); // ctx.getBean("student") 
		
		// haksang의 calculator를 이용한 메소드를 호출합니다!
		haksang.getCalculator().add(5, 2);
		haksang.getCalculator().div(5, 2);
		haksang.getCalculator().mul(5, 2);
		haksang.getCalculator().sub(5, 2);
		
		// 사용한 자원 반납합시다! (생략 가능합니다)
		ctx.close();
			
		
		
		
		}

}
