package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(BlogVo blogVo) {
		System.out.println("[BlogDao.insertBlog()]");
		
		return sqlSession.insert("blog.insertBlog", blogVo);
	}
	
	public BlogVo getBlog(String id) {
		
		System.out.println("[BlogDao.getBlog()]");
		
		BlogVo blogVo = sqlSession.selectOne("blog.selectBlog",id);
		
		System.out.println(blogVo);
		
		return blogVo;
	}
	
	public int updateBlogBasic(String id, String saveName, String blogTitle) {
		
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("id", id);
		blogMap.put("saveName", saveName);
		blogMap.put("blogTitle", blogTitle);
		
		return sqlSession.update("blog.updateBlogBasic", blogMap);
	}
	
	public int updateBlogTitle(String id, String blogTitle) {
		
		Map<String, Object> blogMap = new HashMap<String, Object>();
		blogMap.put("id", id);
		blogMap.put("blogTitle", blogTitle);
		
		return sqlSession.update("blog.updateBlogTitle", blogMap);
	}


}
