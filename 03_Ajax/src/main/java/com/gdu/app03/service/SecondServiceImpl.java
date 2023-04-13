package com.gdu.app03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.BmiVo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecondServiceImpl implements ISecondService {

	@Override
	public BmiVo execute1(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			// bmi = 몸무게 / 키(m) * 키(m) obesity 넘기기
			double weight = Double.parseDouble(request.getParameter("weight"));
			double height = Double.parseDouble(request.getParameter("height")) / 100;
			
			double bmi = weight / (height * height);
			String obesity = null;
			if(bmi < 18.5) {
				obesity = "저체중";
			} else if(bmi < 24.9) {
				obesity = "정상";
			} else if(bmi < 29.9) {
				obesity = "과체중";
			} else {
				obesity = "비만";
			}
			
			return new BmiVo(weight, height, bmi, obesity); // request라서 가능한 코드 $.ajax의 sucess로 넘기는 값
		}catch (Exception e) {
			try {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("몸무게와 키 입력을 확인하세요."); // $.ajax의 error로 넘어간다.	
				out.flush();
				out.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public Map<String, Object> execute2(BmiVo bmiVO) {
		
		try {
			double weight = bmiVO.getWeight();
			double height = bmiVO.getHeight() / 100;
			
			double bmi = weight / (height * height);
			String obesity = null;
			if(bmi < 18.5) {
				obesity = "저체중";
			} else if(bmi < 24.9) {
				obesity = "정상";
			} else if(bmi < 29.9) {
				obesity = "과체중";
			} else {
				obesity = "비만";
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bmi", bmi);
			map.put("obesity", obesity);
			
			return map; 
		}catch (Exception e) {
	
		}
		return null;

	}

}
