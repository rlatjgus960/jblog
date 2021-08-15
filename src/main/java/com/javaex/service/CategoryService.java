package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private BlogDao blogDao;
	
	public BlogVo getBlog(String id) {
		System.out.println("[BlogService.getBlog()]");

		return blogDao.getBlog(id);
	}
	
	//카테고리 추가 및 추가된 카테고리 정보 가져오기
	public CategoryVo addCategory(CategoryVo categoryVo) {
		
		System.out.println("[CategoryService.addCategory()]");
		
		System.out.println(categoryVo);
		int count = categoryDao.insertCateKey(categoryVo);
		System.out.println(categoryVo);
		
		int cateNo = categoryVo.getCateNo();
		
		CategoryVo resultVo = categoryDao.getOneCate(cateNo);
		
		return resultVo;
	}
	
	//카테고리 목록 출력용 리스트 가져오기
	public List<CategoryVo> getCateList(String id) {
		
		System.out.println("[CategoryService.getCateList()]");
		
		return categoryDao.getCateList(id);
	}
	
	//카테고리 삭제
	public int delCate(int cateNo) {
		
		System.out.println("[CategoryService.delCate()]");
		
		return categoryDao.deleteCate(cateNo);
	}
	
	//카테고리 이름 목록
	public List<PostVo> getCategory(String id){
		System.out.println("[CategoryService.getCategory()]");
		
		return categoryDao.getCategory(id);
	}
	
	
	
	

}
