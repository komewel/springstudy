package com.gdu.app03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app03.domain.Person;

public class FirstServiceImpl implements IFirstService {

	
	// 인터페이스(만 I를 붙인다)만들어 놓고서 따로 클래스 이름을 뒤에 붙이는게 실무 패턴이다.
	@Override
	public Person execute1(HttpServletRequest request, HttpServletResponse response) {
		// 최종으로 여기 request까지 파라미터 값이 전달된다. first.jsp에서 ajax로 준 값이
		
		// 예외 발생 시 예외 메시지를 화면으로 전달하기 
		try {
			String name = request.getParameter("name");
			name = name.isEmpty() ? "홍길동" : name; // 사용자가 입력한 name이 없으면 빈 문자열이 전달된다 null이 아니라.
			
			String strAge = request.getParameter("age");
			strAge = strAge.isEmpty() ? "0" : strAge; // 사용자가 입력한 age가 없으면 빈 문자열이 전달된다.
			int age = Integer.parseInt(strAge);	
			// 0~100 범위를 벗어난 경우 예외 발생시키기
			if(age < 0 || age > 100) {
				throw new RuntimeException(age + "살은 잘못된 나이입니다.");
			}
			return new Person(name, age); //하나의 Person을 만들어준거랑 동일하다.
		} catch (Exception e) {
			try { // catch의 응답은 error로 넘어간다.
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());	// $.ajax의 error로 넘기는 예외메시지
				out.flush();
				out.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}
	
	// 하나의 인터페이스에 여러개의 메소드를 배치해서 하나의 클래스에서 모든 기능을 구현할 수 있다.(서피스 구현방식)
	@Override
	public Map<String, Object> execute2(String name, int age) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", age);
		
		return map;
	}

	@Override
	public Map<String, Object> execute3(Person person) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", person.getName());
		map.put("age", person.getAge());
		return map;
		
	}
}
