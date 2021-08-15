package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired BlogDao blogDao;
	

	
	public BlogVo getBlog(String id) {
		System.out.println("[PostService.getBlog()]");

		return blogDao.getBlog(id);
	}
	
	public int writePost(PostVo postVo) {
		System.out.println("[PostService.writePost()]");
		
		return postDao.insertPost(postVo);
	}

}
