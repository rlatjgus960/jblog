package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//로그인폼
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";
	}
	
	//회원가입폼
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		
		System.out.println("[UserController.join()]");
		
		int count = userService.joinUser(userVo);
		
		return "user/joinSuccess";
	}
	
	//ajax 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/idCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("[UserController.idCheck()]");
		
		System.out.println(userService.idCheck(id));
		
		return userService.idCheck(id);
	}
	
	//로그인
	@RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		
		System.out.println("[UserController.login()]");
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) {
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
		} else { // 로그인 실패하면(authUser null 이면 )
			System.out.println("[로그인실패]");
			return "redirect:/user/loginForm?result=fail";
		}
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		
		System.out.println("[UserController.logout()]");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	

}
