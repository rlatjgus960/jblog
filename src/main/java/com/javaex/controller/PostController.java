package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping(value="/{id}/admin")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(@PathVariable("id") String id, Model model) {
		System.out.println("[PostController.writeForm()]");
		
		List<PostVo> postCateList = categoryService.getCategory(id);
		
		model.addAttribute("postCateList", postCateList);
		
		BlogVo blogVo = postService.getBlog(id);

		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("[PostController.write()]");
		
		int count = postService.writePost(postVo);
		
		
		return "redirect:/{id}/admin/writeForm";
	}
	
	

	
	
}
