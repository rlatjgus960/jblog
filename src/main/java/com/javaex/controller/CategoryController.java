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
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping(value="/{id}/admin")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//카테고리 관리 페이지 로딩
	@RequestMapping(value = "/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String category(Model model, @PathVariable("id") String id) {
		System.out.println("[BlogController.category()]");
		
		BlogVo blogVo = categoryService.getBlog(id);

		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping(value="/addCategory", method = {RequestMethod.GET, RequestMethod.POST})
	public CategoryVo addCategory(@ModelAttribute CategoryVo categoryVo) {
		
		System.out.println("[CategoryController.addCategory()]");
		System.out.println(categoryVo);
		
		CategoryVo resultVo = categoryService.addCategory(categoryVo);
		
		
		return resultVo;
	}
	
	//카테고리 렌더링
	@ResponseBody
	@RequestMapping(value="/cateList", method = {RequestMethod.GET, RequestMethod.POST})
	public List<CategoryVo> cateList(@PathVariable("id") String id) {
		System.out.println("[CategoryController.cateList()]");
		
		List<CategoryVo> cateList = categoryService.getCateList(id);
		
		return cateList;
	}
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/delCate", method = {RequestMethod.GET, RequestMethod.POST})
	public int delCate(@RequestParam("cateNo") int cateNo) {
		System.out.println("[CategoryController.delCate()]");
		
		return categoryService.delCate(cateNo);
	}

}
