package com.javaex.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
		
	@RequestMapping(value="/{id}")
	public String personId() {
		System.out.println("[BlogController.personId()]");
		
		
		
		return "blog/blog-main";
	}
	
}
