package com.aroundog.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aroundog.model.domain.Adopt;

@Repository
public class MybatisAdoptDAO implements AdoptDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	public int insert(Adopt adopt) {	
		return sessionTemplate.insert("Adopt.insert", adopt);
	}
}