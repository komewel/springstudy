package com.gdu.app03.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.gdu.app03.domain.BmiVo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecondServiceImpl implements ISecondService {

	@Override
		public ResponseEntity<BmiVo> execute1(HttpServletRequest request) {
			
		try {
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
			return new ResponseEntity<BmiVo>(new BmiVo(weight, height, bmi, obesity), HttpStatus.OK);
			// HttpStatus.OK = 200 , 그냥 200 쓰면 없어 보이니까 
			//
		}catch (Exception e) {
			
			BmiVo bmiVO = null;
			return new ResponseEntity<BmiVo>(bmiVO , HttpStatus.INTERNAL_SERVER_ERROR);  
			// 첫번째 인수에는 에러가 났으니 다른 데이터는 안줘도 된다 null로   
			// HttpStatus.INTERNAL_SERVER_ERROR = 500, 응답코드만 보내준거다
			// return new ResponseEntity<BmiVo>(null , HttpStatus.OK);  
			// 위 코드처럼 직접적인 null 말고 타입은 맞춰줘라 다만(그렇지만) 실질적인 데이터는 null이다.
			// HttpStatus가 500이므로 알아서 ajax error에서 처리된다, 오류니까
		}
		
		}
	@Override
	public ResponseEntity<Map<String, Object>> execute2(BmiVo bmiVO) {
		// 아무런 기입없이 버튼을 누르면 null을 double 타입 변환을 내부에서 시도하다가 numberformatException이 발생한다.
		// 프론트에서 if()문으로 잘못된 데이터가 안넘어 오도록 막았으니 걱정 말자
		
		double weight = bmiVO.getWeight();
		double height = bmiVO.getHeight();
		
		if(weight == 0 || height == 0) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST); // 응답코드가 정상(200)이 아니므로 $.ajax의 error로 전달된다. try catch가 아니여도 수행할수 있는 특징이 있다.
		}
			
		double bmi = weight / (height * height / 10000);
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
		
		// 실제로 응답할 데이터 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bmi", bmi);
		map.put("obesity", obesity);
		
		// 응답 헤더(Content-Type)
		MultiValueMap<String, String> header = new HttpHeaders();
		// HttpHeaders 인터페이스 타입이 있는데(상속받은) multivalueMap 이놈이다, 그래서 위처럼 해괴망측한 코드가 가능하다
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE); // 여기서 produces 작업을 해주는거다
		// 원래 map은 put인데 add이다 이거는 왠지는 모르겠지만;;ㅜ
		
	
		return new ResponseEntity<Map<String,Object>>(map, header, HttpStatus.OK);
		// 3개짜리 생성자를 써서 보내보자 content-Type을 이용해서
		
	}
	/*
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
			
			return new BmiVo(weight, height, bmi, obesity); // request라서 가능한 코드 $.ajax의 sucess로 넘어가는 값(여기서 넘어가는 값은 bean이다, success로 넘어가는 이유는 try문안에 있어서)
		}catch (Exception e) { // catch문 자체가 예외 처리하는 부분이므로 ajax에서 error문으로 넘어간다.
			try {
				response.setContentType("text/plain; charset=UTF-8"); // 응답해주면 jqxhr.responsetext로 넘어간다.
				PrintWriter out = response.getWriter();
				out.println("몸무게와 키 입력을 확인하세요."); // $.ajax의 error로 넘어가는 예외메시지.	
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
	*/
	
	/*
	 	ResponseEntity<T> 클래스
	 	1. Ajax 응답 데이터를 생성하는 클래스
	 	2. 생성자 중 하나의 사용법
	 		public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) 
	 		1) @Nullable T body 					  : 실제로 응답할 데이터, @Nullable, 응답 데이터가 null이여도 오류가 발생하지 않게 되어있다
	 		2) MultiValueMap<String, String> headers  : 응답 헤더 (대표적으로 Content-Type)
	 		3) HttpStatus status					  : 응답 코드(200, 404, 500 등), 200이면 success에서 404, 500이면 error쪽에서 처리를 해준다. 별도의 try catch문이 없어도 success나 error를 처리가능하다.
	 */
}
