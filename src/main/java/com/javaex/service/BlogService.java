package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	/*
	 * public int createBlog(BlogVo blogVo) {
	 * System.out.println("[BlogService.createBlog()]");
	 * 
	 * return blogDao.insertBlog(blogVo); }
	 */

	public BlogVo getBlog(String id) {
		System.out.println("[BlogService.getBlog()]");

		return blogDao.getBlog(id);
	}

	// 기본 수정
	public int basicUp(String id, String blogTitle, MultipartFile file) {
		System.out.println("[FileupService.basicUp()");

		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize " + fileSize);

		if (fileSize > 0) {

			String saveDir = "C:\\javaStudy\\upload\\";

			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());

			// 원파일이름
			String orgName = file.getOriginalFilename();
			System.out.println(orgName);

			// 확장자
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			System.out.println(exName);

			// 저장파일이름(관리때문에 겹치지 않는 새 이름 부여)
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			System.out.println(saveName);

			// 파일패스
			String filePath = saveDir + "\\" + saveName;
			System.out.println(filePath);

			// 파일 서버하드디스크에 저장
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);
				bout.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 로고 경로, 블로그 타이틀 업데이트
			return blogDao.updateBlogBasic(id, saveName, blogTitle);

		} else {
			return blogDao.updateBlogTitle(id, blogTitle);

		}

	}
	
	
	//메인페이지 포스트 출력
	public List<PostVo> getPost(String id, int cateNo) {
		System.out.println("[BlogService.getPost()]");
		
		return postDao.getPost(id, cateNo);
	}
	
	//최근 포스트 출력
	public PostVo getLatestPost(String id, int cateNo) {
		System.out.println("[BlogService.getLatestPost()]");
		
		return postDao.getLatestPost(id, cateNo);
	}
	
	
}
