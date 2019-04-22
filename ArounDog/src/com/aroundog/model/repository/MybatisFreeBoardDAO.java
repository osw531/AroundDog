package com.aroundog.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MybatisFreeBoardDAO implements FreeBoardDAO{	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	//��� �����Խ��� �� ��������
	public List selectAll() {
		List freeBoardList=sqlSessionTemplate.selectList("FreeBoard.selectAll");
		return freeBoardList;
	}

}
