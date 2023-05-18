package com.gdu.app12.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

	// 로그인 여부를 확인해서
	// 로그인이 되어 있지 않으면 로그인 페이지로 이동시키는 인터셉터
	// pre: 이전 post: 나중 after: 끝나고 난 후 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		// 로그인 여부 확인
		if(session != null && session.getAttribute("loginId") == null) {
			
			// 응답 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
				out.print("if(confirm('로그인이 필요한 기능입니다. 로그인 하시겠습니까?')){");
				out.print("location.href='" + request.getContextPath() + "/user/login.form';");
				out.print("} else {");
				out.print("history.back();");
				out.print("}");
				out.print("</script>");
				out.flush();
				out.close();
				
				return false; // 이부분이 aspect랑 intercept의 차이가 발생하는 부분이다, 컨트롤러의 요청이 처리되지 않는다(굳이 컨트롤러를 안돌려도 되는데 돌려진다 aspect는), 로그인이 되어 있지 않다면 응답을 만들어서 끝낸다.
				
				}
			return true; // 컨트롤러 요청이 처리된다.
	}
	
	
}
