package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class CategoryDao {
	
	@Autowired private SqlSession sqlSession;
	
	//기본 카테고리 추가
	public int insertCate(CategoryVo categoryVo) {
		System.out.println("[CategoryDao.insertCate()]");
		
		System.out.println(categoryVo);
		
		int count = sqlSession.insert("category.insertCate", categoryVo);
		
		System.out.println(count);
		
		return sqlSession.insert("category.insertCate", categoryVo);
	}
	
	public int insertCateKey(CategoryVo categoryVo) {
		System.out.println("[CategoryDao.insertCateKey()]");
		
		return sqlSession.insert("category.insertCateKey", categoryVo);
	}
	
	public CategoryVo getOneCate(int cateNo) {
		System.out.println("[CategoryDao.getCate()]");
		
		return sqlSession.selectOne("category.getOneCate", cateNo);
	}
	
	public List<CategoryVo> getCateList(String id) {
		System.out.println("[CategoryDao.getCateList()]");
		
		return sqlSession.selectList("category.getCateList", id);
	}

	//write폼으로 보내줄 카테고리 가져오기
	public List<PostVo> getCategory(String id){
		System.out.println("[CategoryDao.getCategory()]");
		
		return sqlSession.selectList("category.getCateName", id);
	}
	
	//카테고리 삭제
	public int deleteCate(int cateNo) {
		System.out.println("[CategoryDao.getCategory()]");
		
		return sqlSession.delete("category.deleteCate", cateNo);
	}
	
	//최근 카테고리번호 가져오기
	public CategoryVo getLatestCate(String id) {
		System.out.println("[CategoryDao.getLatestCate()]");
		
		return sqlSession.selectOne("category.getLatestCate",id);
	}
}
