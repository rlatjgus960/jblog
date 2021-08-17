package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	// 블로그 메인
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String personId(Model model, @PathVariable("id") String id, @ModelAttribute BlogVo blogVo) {
		System.out.println("[BlogController.personId()]");

		System.out.println(id);
		BlogVo getBlogVo = blogService.getBlog(id);
		System.out.println(getBlogVo);
		
		// 카테고리 이름 목록 가져오기
		List<PostVo> cateList = categoryService.getCategory(id);
		
		
		//최근 카테고리번호 가져오기, 카테고리 눌렀을때 글 제목 목록 출력
		CategoryVo cateVo = blogService.getLatestCate(id);
		
		int cateNo = 0;
		
		if(blogVo.getCateNo() == 0) {
			cateNo = cateVo.getCateNo();
		} else {
			cateNo = blogVo.getCateNo();
		}
		
		List<PostVo> postList = blogService.getPost(id, cateNo);

		// 최근 포스트 출력
		PostVo latestPost = blogService.getLatestPost(id, cateNo);

		if (getBlogVo != null) {
			model.addAttribute("blogVo", getBlogVo);
			model.addAttribute("cateList", cateList);
			model.addAttribute("postList", postList);
			model.addAttribute("latestPost", latestPost);
			
			return "/blog/blog-main";
		}else {
			return "/error/403";
		}

		
	}

	// 블로그 기본관리
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String basic(Model model, @PathVariable("id") String id) {

		System.out.println("[BlogController.basic()]");

		BlogVo blogVo = blogService.getBlog(id);

		model.addAttribute("blogVo", blogVo);

		return "/blog/admin/blog-admin-basic";
	}

	// 블로그 기본관리 수정
	@RequestMapping(value = "/{id}/admin/basicUp", method = { RequestMethod.GET, RequestMethod.POST })
	public String basicUp(Model model, @RequestParam("file") MultipartFile file,
			@RequestParam("blogTitle") String blogTitle, @PathVariable("id") String id) {
		System.out.println("[BlogController.basic()]");

		blogService.basicUp(id, blogTitle, file); // 서비스한테서 받음

		return "redirect:/{id}";
	}

}
