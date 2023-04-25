package com.gdu.app09.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app09.service.EmployeeListService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeListService employeeListService;
	// 정보량이 많아 서비스가 직접 전달해준다.
	
	@GetMapping("/employees/pagination.do")
	public String pagination(HttpServletRequest request, Model model) {
		employeeListService.getEmployeeListUsingPagination(request, model);
		return "employees/pagination"; 
	}
	
	@GetMapping("/employees/change/record.do") // session 그냥 선언해서 사용할 수 도 있다
	public String changeRecord(HttpSession session
							 , HttpServletRequest request
			    			 , @RequestParam(value="recordPerPage", required = false, defaultValue = "10") int recordPerPage) { // 인수에는 선언하고 싶은거 크게 신경안쓰고 선언해도 된다.
		session.setAttribute("recordPerPage", recordPerPage);
		return "redirect:" + request.getHeader("referer");  //  바로 직전에 방문 했던 주소는 request에 header에 기록된다, 현재 주소(/employees/change/record.do)의 이전 주소(referer)로 이동하시오.
		
	}
}
