package com.aroundog.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MybatisFreeBoardDAO implements FreeBoardDAO{	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	//모든 자유게시판 글 가져오기
	public List selectAll() {
		List freeBoardList=sqlSessionTemplate.selectList("FreeBoard.selectAll");
		return freeBoardList;
	}

}
