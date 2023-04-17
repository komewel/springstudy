package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.NoticeService;
import service.NoticeServiceImpl;

@WebServlet("*.no")

public class NoticeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		ActionForward af = null;
		
		// NoticeServiceImpl 객체 생성
		NoticeService service = new NoticeServiceImpl();
		
		// 요청에 따른 서비스 실행
		switch(urlMapping) {
		case "/notice/list.no":
			af = service.findAllNotices(request);
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
