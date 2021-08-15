package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertPost(PostVo postVo) {
		System.out.println("[PostDao.insertPost()]");
		
		return sqlSession.insert("post.insertPost", postVo);
	}

	//메인페이지 포스트 출력
	public List<PostVo> getPost(String id, int cateNo) {
		System.out.println("[PostDao.getPost()]");
		
		Map<String, Object> postMap = new HashMap<String, Object>();
		postMap.put("id", id);
		postMap.put("cateNo", cateNo);
		
		System.out.println(sqlSession.selectList("post.getPostList", postMap));
		
		return sqlSession.selectList("post.getPostList", postMap);
	}

}
