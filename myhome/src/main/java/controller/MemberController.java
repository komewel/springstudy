package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("*.me")

public class MemberController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		ActionForward af = null;
		
		// 기존 구조와 조금 다름(하나의 서비스에 여러 기능을 넣는 스프링 방식으로 수정하였음)
		MemberService service = new MemberServiceImpl();
		
		// 기존 구조는 switch가 서비스 생성이지만 현재 구조는 서비스 실행임
		switch(urlMapping) {
		case "/member/login.me":
			af = service.login(request, response);
			break;
		case "/member/logout.me":
			af = service.logout(request, response);
			break;
		case "/member/join.me":
			af = new ActionForward("/member/join.jsp", false);
			break;
		case "/member/register.me":
			service.register(request, response);  // af 없이 register() 메소드 내부에서 직접 이동
			break;
		case "/member/cancel.me":
			service.cancel(request, response);  // af 없이 cancel() 메소드 내부에서 직접 이동
			break;
		
		// 매핑을 잘못 작성한 경우
		default:
			System.out.println("매핑을 확인하세요.");
		}
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
