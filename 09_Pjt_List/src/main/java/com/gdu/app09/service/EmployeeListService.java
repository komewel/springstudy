package com.gdu.app09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeListService {

	public void getEmployeeListUsingPagination(HttpServletRequest request, Model model);
	public Map<String, Object> getEmployeeListUsingScroll(HttpServletRequest request);
	public void getEmployeeListUsingSearch(HttpServletRequest request, Model model);
	public Map<String, Object> getAutoComplete(HttpServletRequest request); // ajax이기 때문에, response entity를 써도 된다 안에 map을 넣어서
	
}
