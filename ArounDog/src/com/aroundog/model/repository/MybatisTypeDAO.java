package com.aroundog.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aroundog.model.domain.Type;

@Repository
public class MybatisTypeDAO implements TypeDAO{
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public Type select(String info) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("Type.select", info);
	}

}
