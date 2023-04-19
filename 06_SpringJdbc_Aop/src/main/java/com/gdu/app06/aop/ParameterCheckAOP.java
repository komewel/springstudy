package com.gdu.app06.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component // Spring Container에 ParameterCheckAOP 객체를 Bean으로 만든다
@Aspect	   // ParameterCheckAOP 클래스는 AOP동작을 위한 클래스이다.
// @EnableAspectJAutoProxy DBconfig에서 이미 작성하였기 때문에 필요없다 
public class ParameterCheckAOP { // 메소드마다 파라미터 체크하는 역할, 이렇게 만들면 일일이 하나하나 설정해 줄 필요없이(반복적이고 중복되는 행위를 안하고) 자동으로 찍어준다.

	// 포인트컷(어떤 메소드에 어드바이스(AOP 동작)을 적용할 것인가?), 컨트롤러에 ParamCheck로 끝나게 설정한 애들
	@Pointcut("execution(* com.gdu.app06.controller.*Controller.*ParamCheck(..))")
	public void setPointCut() {// 이기능을 임의의 메소드에 저장한다.
		// 이 메소드는 이름만 제공하는 역할(아무 이름이나 써도 되고, 본문도 필요가 없다, 바지사장같은 역할)
	}
	
	// 어드바이스(포인트컷에서 실제로 동작할 작업 : 파라미터들의 값을 LOGGER를 이용해서 콘솔로 확인)
	
	// 파라미터를 콘솔에 출력하기 위한 LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterCheckAOP.class);

	@After("setPointCut()") // 위에 바지사장 메소드가 여기 들어감 @Around는 오류가 발생해도 로그가 찍힌다, 그에반해 @After는 메소드 실행후에 로그를 찍어준다.
	public void paramLogging(JoinPoint joinPoint) throws Throwable {
		
		// 모든 파라미터가 저장된 HttpServletRequest 가져오기 
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		// HttpServletRequest -> Map으로 변환하기
		// 모든 파라미터가 Map의 Key로 변환된다. Map의 Key는 반복문으로 모두 순회할 수 있다. (핵심은, 파라미터 이름을 몰라도 된다.)
		Map<String, String[]> map = request.getParameterMap(); 
	
		// 콘솔에 출력할 형태 만들기
		// [파라미터명=값] 
		String str = "";
		if(map.isEmpty()) {
			str += "[No Parameter]";
		} else {
			for(Entry<String, String[]> entry : map.entrySet()) {
				str += "[" + entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "]"; // Arrays.toString(entry.getValue()), 자바에서 배열을 문자열로 바꿔주는 기능을 빌렸다. 
			}
		}
		// 여기서 콘솔에 string str을 로그로 남긴다.(성공하든 실패하든 찍힌다.)
		// 치환 문자 : {}  
		LOGGER.debug("{} {} {}", request.getMethod(), request.getRequestURI(), str);
			
			// 어드바이스 실행
			/*
			Object obj =null;
			try {
				obj= joinPoint.proceed(); // 실제 실행은 여기다
			}catch (Exception e) {
				throw e; // paramLogging() 메소드가 throw로 처리했으니 동일하게
			}finally {
			}
			return obj;
			}
			 */
	}
}
