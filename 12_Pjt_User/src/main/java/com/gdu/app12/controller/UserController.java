package com.gdu.app12.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app12.service.UserService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

  // field
  private final UserService userService;
  
  @GetMapping("/agree.form")
  public String agreeForm() {
    return "user/agree";
  }
  
  @GetMapping("/join.form")
  public String joinForm(@RequestParam(value="location", required=false) String location  // 파라미터 location이 전달되지 않으면 빈 문자열("")이 String location에 저장된다.
                       , @RequestParam(value="event", required=false) String event        // 파라미터 event가 전달되지 않으면 빈 문자열("")이 String event에 저장된다.
                       , Model model) {
    model.addAttribute("location", location);
    model.addAttribute("event", event);
    return "user/join";
  }
  
  @ResponseBody
  @GetMapping(value="/verifyId.do", produces="application/json")
  public Map<String, Object> verifyId(@RequestParam("id") String id) {
    return userService.verifyId(id);
  }
  
  @ResponseBody
  @GetMapping(value="/verifyEmail.do", produces="application/json")
  public Map<String, Object> verifyEmail(@RequestParam("email") String email) {
    return userService.verifyEmail(email);
  }
  
  @ResponseBody
  @GetMapping(value="/sendAuthCode.do", produces="application/json")
  public Map<String, Object> sendAuthCode(@RequestParam("email") String email) {
    return userService.sendAuthCode(email);
  }
  
  @PostMapping("/join.do")
  public void join(HttpServletRequest request, HttpServletResponse response) {
    userService.join(request, response);
  }
  
  @GetMapping("/login.form")
  public String loginForm(@RequestHeader("referer") String url, Model model) { // @RequestHeader : 스프링에서 지원하는 기능
    
    // 요청 헤더 referer : 로그인 화면으로 이동하기 직전의 주소를 저장하는 헤더 값
    model.addAttribute("url", url);
    
    return "user/login";
    
  }
  
  @PostMapping("/login.do")
  public void login(HttpServletRequest request, HttpServletResponse response) {
    userService.login(request, response);
  }
  
  @GetMapping("/logout.do")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    // 로그인이 되어 있는지 확인
    userService.logout(request, response);
    return "redirect:/";
  }
  
  @GetMapping("/leave.do")
  public void leave(HttpServletRequest request, HttpServletResponse response) {
    // 로그인이 되어 있는지 확인
    userService.leave(request, response);
  }
  
  @GetMapping("/wakeup.form")
  public String wakeup() {
	  return "user/wakeup";
  }
  
  @GetMapping("/restore.do")
  public void restore(HttpSession session) {
	  // 복원할 회원의 아이디를 sysout으로 출력해 보시오.
	 System.out.println(session.getAttribute("sleepUserId"));
	 // 휴면계정을 정상계정으로 복구할 시 휴면테이블에서는 정보를 지우고 정상테이블에는 카피해서 넣어주고, 복원을 했는데 로그인을 안시키면 최종로그인이 최신화가 안되어서 휴면계정으로 다시 복구 되기 때문에 강제 로그인도 한번 시켜줘야 한다.
	  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}