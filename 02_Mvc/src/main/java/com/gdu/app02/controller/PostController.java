package com.gdu.app02.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller 
public class PostController {

	@GetMapping("/post/detail.do")
	public String detail(HttpServletRequest request) throws Exception { // name, age 파라미터가 있다.
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		System.out.println("/post/detail.do"); 
		System.out.println("name: " + name + ", age: " + age);
		
		// return "redirect: 이동경로";
		return "redirect:/post/list.do?name=" + URLEncoder.encode(name, "UTF-8") + "&age=" + age; // redirect 뒤에 있는건 매핑으로 해석된다.
																	  // 리다이렉트로 넘겨줄때마다 매핑뒤에 파라미터값을 작성해서 넘겨주어야 한다.
																	  // 포워드와 달리 리다이렉트는 파라미터값이 끊어지기 때문에 다시 붙여줘야 한다.
																	  // /post/list.do 매핑으로 이동하시오! name, age 파라미터를 다시 붙인다!!
																	  // 파라미터를 받는건 request이다.
		// 리다이렉트를 시켜줄땐 jsp 파일명이 아니라 새로운 매핑 주소를 알려줘야 한다(패턴에 맞지 않는다, 꼭 매핑이여야 한다.)
		// 포워드 하실땐 이동결로가 jsp 파일명이다.
		// 삽입수정삭제 할때는 리다이렉트가 쓰인다 대게 저작업이 끝나고 난뒤 목록으로 돌아가는것이 룰이다	
		// 포워드 하겠다 그럼 jsp로 리다이렉트 하겠다 하면 매핑으로 이동	
		
	}
	// public String detail(@RequestParam("name") String name, @RequestParam("age") int age){}
	// public String detail(Person p) {}

	@GetMapping("/post/list.do") // 위에 return 값으로 여기로 바로 넘어온다고 보면 된다.
	public String list(HttpServletRequest request,	// 파라미터값이 안넘어왔다 리다이렉트라서 포워드만 넘어온다.
									Model model) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		// WEB-INF/views/post/list.jsp로 forward 하겠다!
		// 뷰 리졸버가 알아서 프리픽스 값으로 넣어서 인식해준다.
		return "post/list";
	}
	
	@GetMapping("/post/detail.me")
	public String detailMe(HttpServletRequest request,
						   RedirectAttributes redirectAttributes) { // 만약 Redirect할 때 속성(Attribute)을 전달하는 스프링 인터페이스 원래는 파라미터값이 안주어주면 자동으로 안넘어가는데 그기능을 알아서 해주는 기능
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
	
		// Redirect 경로까지 전달되는 속성 : Flash Attribute 주의. addAttribute를 사용하면 model이랑 동일하므로 redirect로 전달은 안된다 
		redirectAttributes.addFlashAttribute("name", name);
		redirectAttributes.addFlashAttribute("age", age);
		
		return "redirect:/post/list.me";
	}
	
	@GetMapping("/post/list.me")
	public String listMe() { 
	// FlashAttribute는 Redirect 경로까지 자동으로 전달되므로 별도의 코드가 필요하지 않다.
		return "post/list";
	}
	
}
