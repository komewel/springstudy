package com.gdu.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	/*
	@ResponseBody
	@GetMapping(value="/list.json", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<StaffDTO> list1() {
		return staffService.getStaffList1();
	}
	*/

	@GetMapping("/list.json") // ResponseEntity 는 응답형식(produces)이랑 @ResponseBody 기능을 동시에 탑재한다
	public ResponseEntity<List<StaffDTO>> list2() {
		return staffService.getStaffList2();
	}
	
	/*
	@ResponseBody
	@PostMapping(value="/add.do", produces="text/plain; charset=UTF-8")
	public String add1(HttpServletRequest request) {
		return staffService.addStaff1(request);
	}
	*/
	
	@PostMapping("/add.do")
	public ResponseEntity<String> add2(StaffDTO staffDTO) {
		return staffService.addStaff2(staffDTO);
	}
	
	
}
