package com.gdu.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffMapper;
	
	/*
	@Override // 얘는 responseentity로 json이라고 응답을 해줬고
	public List<StaffDTO> getStaffList1() {
		List<StaffDTO> staffList = staffMapper.getStaffList();
		return staffList;
	}
	
	*/
	
	@Override // 얘는 여기서 응답을 하고
	public ResponseEntity<List<StaffDTO>> getStaffList2() {
		List<StaffDTO> staffList = staffMapper.getStaffList();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<StaffDTO>>(staffList, header, HttpStatus.OK);
	}
	
	/*
	@Override
	public String addStaff1(HttpServletRequest request) {
		try {
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			StaffDTO staffDTO = new StaffDTO();
			staffDTO.setSno(sno);
			staffDTO.setName(name);
			staffDTO.setDept(dept);
			staffMapper.addStaff(staffDTO); // 예시) 사원번호가 5 BYTE 초과되거나 중복되거나 NULL인 경우 예외 발생  
			return "사원 등록이 성공했습니다.";
		} catch (Exception e) {
			return "사원 등록이 실패했습니다."; // $.ajax의 error로 전달
		}
	}
	*/
	
	@Override
	public ResponseEntity<String> addStaff2(StaffDTO staffDTO) {
		try {
		staffMapper.addStaff(staffDTO);
		return new ResponseEntity<String>("사원 등록이 성공했습니다.", HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>("사원 등록이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }
	
	@Override
	public ResponseEntity<List<StaffDTO>> searchStaff(String sno) {
		List<StaffDTO> staffList1 = staffMapper.searchStaff(sno);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<StaffDTO>>(staffList1, header, HttpStatus.OK);
	}
}
