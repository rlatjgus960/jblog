package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int joinUser(UserVo userVo) {
		System.out.println("[UserService.joinUser()]");
		
		return userDao.insertUser(userVo);
	}

	//아이디 중복 체크
	public boolean idCheck(String id) {
		
		System.out.println("[UserService.idCheck()]");
		
		UserVo userVo = userDao.getUser(id);
		
		System.out.println(userVo);
		
		if(userVo == null) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
	//로그인
	public UserVo login(UserVo userVo) {
		
		return userDao.getUser(userVo);
	}
	
}
