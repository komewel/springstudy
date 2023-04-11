package com.gdu.app01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	// 임의의 학생 만들고, MainClass에서 확인하기
	@Bean
	public Student stu() {
		
		List<Integer> scores = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) {
			scores.add((int)(Math.random() * 101));
		}
		
		Set<String> awards = new HashSet<String>();
			awards.add("개근상");
			awards.add("장려상");
			awards.add("참가상");
			
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("address", "seoul");
		contact.put("tel", "02-1234-5678");
		
		Student student = new Student();
		student.setScores(scores);
		student.setAwards(awards);
		student.setContact(contact);
		return student;

	}
	
}
