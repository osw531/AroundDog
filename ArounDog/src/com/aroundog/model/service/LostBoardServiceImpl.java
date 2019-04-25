package com.aroundog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aroundog.common.exception.EditFailException;
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

	@Override
	public LostBoard select(int lostboard_id) {
		return lostBoardDAO.select(lostboard_id);
	}

	@Override
	public List selectImg(int lostboard_id) {
		return lostBoardDAO.selectImg(lostboard_id);
	}

	@Override
	public void update(int lostboard_id) throws EditFailException{
		int result = lostBoardDAO.update(lostboard_id);
		if(result==0){
			throw new EditFailException("수정 실패");
		}	
	}

	@Override
	public LostBoardImg selectThumb(int lostboard_id) {
		return lostBoardDAO.selectThumb(lostboard_id);
	}

	@Override
	public List selectAllImg() {
		return lostBoardDAO.selectAllImg();
	}
}
