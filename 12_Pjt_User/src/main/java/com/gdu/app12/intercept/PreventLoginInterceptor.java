package com.gdu.app12.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PreventLoginInterceptor implements HandlerInterceptor {

	// preventLoginInterceptor
	// 로그인이 되어 있는 상태에서
	// 다시 로그인 페이지로 이동, 회원가입 페이지로 이동 등을 막는 인터셉트, "해당기능은 사용할 수 없습니다"
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		
		HttpSession session = request.getSession();
		
		// 로그인 여부 확인
		if(session != null && session.getAttribute("loginId") != null) {
			
			// 응답 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
				out.print("alert('해당기능은 사용할 수 없습니다.');");
				out.print("history.back();");
				out.print("</script>");
				out.flush();
				out.close();
				
		return false;
	}
		return true;
	
}
	
}
