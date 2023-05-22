package com.gdu.movie.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class GenreComedy implements HandlerInterceptor {

	@Autowired
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String title = request.getParameter("title"); 
		String genre = request.getParameter("genre"); 
		String description = request.getParameter("description"); 
		String star = request.getParameter("star"); 
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
