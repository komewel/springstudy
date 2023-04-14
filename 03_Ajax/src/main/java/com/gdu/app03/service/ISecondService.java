package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.BmiVo;

public interface ISecondService {
	//public BmiVo execute1(HttpServletRequest request, HttpServletResponse response); 
	//public Map<String, Object> execute2(BmiVo bmiVO);
	public ResponseEntity<BmiVo> execute1(HttpServletRequest request); // 응답타입은 BmiVo이다
	// ajax 응답 전용 응답타입(응답개체), 응답데이터를 모아서 응답해주는 개체
	public ResponseEntity<Map<String, Object>> execute2(BmiVo bmiVO); 
	
	
	
	
	
	
}
