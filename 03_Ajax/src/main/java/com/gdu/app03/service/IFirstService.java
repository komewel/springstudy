package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.Person;

public interface IFirstService {
	public Person execute1(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> execute2(String name, int age); // 상속받은 클래스가 자기가 못보던게 생기면 오류가 생긴다 정상이다
	public Map<String, Object> execute3(Person person);
	
}
