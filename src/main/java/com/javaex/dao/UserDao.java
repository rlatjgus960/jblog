package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser()]");
		
		return sqlSession.insert("user.insertUser", userVo);
	}
	
	
	//아이디 중복체크용
	public UserVo getUser(String id) {
		System.out.println("[UserDao.getUser()]");
		
		System.out.println(id);
		
		UserVo userVo = sqlSession.selectOne("user.getUserById", id);
		
		System.out.println(userVo);
		
		return userVo;
	}
	
	//로그인
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserDao.getUser()]");
		
		System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.getUserByVo", userVo);
		
		System.out.println(authUser);
		
		return authUser;
	}
	
	
	

}
