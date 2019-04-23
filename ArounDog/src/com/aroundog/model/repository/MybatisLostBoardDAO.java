package com.aroundog.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;

@Repository
public class MybatisLostBoardDAO implements LostBoardDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Override
	public int insert(LostBoard lostBoard) {
		return sessionTemplate.insert("LostBoard.insert", lostBoard);
	}

	@Override
	public int insertImg(LostBoardImg lostBoardImg) {
		return sessionTemplate.insert("LostBoardImg.insert", lostBoardImg);
	}

	@Override
	public List selectAll() {
		return sessionTemplate.selectList("LostBoard.selectAll");
	}

}
