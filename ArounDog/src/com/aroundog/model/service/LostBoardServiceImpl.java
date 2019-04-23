package com.aroundog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aroundog.common.exception.ReportFailException;
import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;
import com.aroundog.model.repository.LostBoardDAO;

@Service
public class LostBoardServiceImpl implements LostBoardService{
	@Autowired
	private LostBoardDAO lostBoardDAO;
	
	@Override
	public void insert(LostBoard lostBoard) throws ReportFailException{
		int result = lostBoardDAO.insert(lostBoard);
		if(result==0) {
			throw new ReportFailException("등록 실패");
		}
	}

	@Override
	public void insertImg(LostBoardImg lostBoardImg) throws ReportFailException{
		int result = lostBoardDAO.insertImg(lostBoardImg);
		if(result==0) {
			throw new ReportFailException("등록 실패");
		}
		
	}

	@Override
	public List selectAll() {
		return lostBoardDAO.selectAll();
	}

}
