package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app13.service.BlogService;

@RequestMapping("/blog")
@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/list.do")
	public String list() {
		return "blog/list";
	}
	
	@GetMapping("/write.form")
	public String write() {
		return "blog/write";
	}
	
	@PostMapping("/add.do")
	public void add(HttpServletRequest request, HttpServletResponse response) { // void로 해놓으니까 return은 없어도되네
		blogService.addBlog(request, response);
	}

	@ResponseBody
	@PostMapping(value="/imageUpload.do", produces="application/json")
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) { // MultipartHttpServletRequest: 요청에 파일첨부가 되어있을때 쓴다 mvc: <input type"file"> ajax : new FormData() 로 처리되는데 그때 쓰는 매개변수는 MultipartHttpServletRequest
		return blogService.imageUpload(multipartRequest);
	}
}
