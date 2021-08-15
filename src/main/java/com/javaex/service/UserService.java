package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BlogDao blogDao;

	// 회원가입
	public int joinUser(UserVo userVo) {
		System.out.println("[UserService.joinUser()]");

		int uCount = userDao.insertUser(userVo);

		// 블로그 생성
		String id = userVo.getId();
		String blogTitle = userVo.getUserName() + "의 블로그입니다.";

		BlogVo blogVo = new BlogVo(id, blogTitle);
		System.out.println(blogVo);

		int bCount = blogDao.insertBlog(blogVo);
		
		// 기본 카테고리 추가
		/*
		 * CategoryDao categoryDao = new CategoryDao(); String title = "미분류"; String
		 * desc = "기본으로 만들어지는 카테고리입니다.";
		 * 
		 * CategoryVo categoryVo = new CategoryVo(id, title, desc);
		 * 
		 * categoryDao.insertCate(categoryVo);
		 */

		return uCount;
	}

	// 아이디 중복 체크
	public boolean idCheck(String id) {

		System.out.println("[UserService.idCheck()]");

		UserVo userVo = userDao.getUser(id);

		System.out.println(userVo);

		if (userVo == null) {
			return true;
		} else {
			return false;
		}

	}

	// 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("[UserService.login()]");

		return userDao.getUser(userVo);
	}

}
