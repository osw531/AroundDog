package com.aroundog.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aroundog.common.exception.EditFailException;
import com.aroundog.common.exception.ReportFailException;
import com.aroundog.common.file.LostBoardImgUploader;
import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;
import com.aroundog.model.domain.ReportImg;
import com.aroundog.model.repository.LostBoardDAO;

@Service
public class LostBoardServiceImpl implements LostBoardService{
	@Autowired
	private LostBoardDAO lostBoardDAO;
	
	private LostBoardImgUploader uploader = new LostBoardImgUploader();
	
	@Override
	public void insert(LostBoard lostBoard) throws ReportFailException{
		int result = lostBoardDAO.insert(lostBoard);
		if(result==0) {
			throw new ReportFailException("등록 실패");
		}
	}

	@Override
	public void insertImg(MultipartFile[] myFile, LostBoard lostBoard, LostBoardImg lostBoardImg,
			String realPath) throws ReportFailException{
		String[] imgList = uploader.returnFilename(myFile, lostBoard, lostBoardImg, realPath);
		//System.out.println("서비스에서 받은 리스트 크기는"+imgList.size());
		int result = 0;
		for(int i=0;i<imgList.length;i++) {
			LostBoardImg lbi = new LostBoardImg();
			lbi.setLostboard(lostBoard);
			lbi.setImg(imgList[i]);
			result = lostBoardDAO.insertImg(lbi);
		}
		if (result == 0) {
			throw new ReportFailException("제보 실패!!");
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

	@Override
	public List getKeyWordList(List lostBoardList) {
		List<LostBoardImg> thumbList = new ArrayList();
		List<Integer> idList= new ArrayList();
		for(int i=0;i<lostBoardList.size();i++) {
		  LostBoard lb = (LostBoard) lostBoardList.get(i);	   
		  int lb_id = lb.getLostboard_id();
		  LostBoardImg lbi=selectThumb(lb_id);
		  lbi.setLostboard_id(lb_id);
		  thumbList.add(lbi);
		  idList.add(lb_id);
		  }
		List keyWordList = new ArrayList();
		keyWordList.add(thumbList);
		keyWordList.add(idList);
		return keyWordList;
	}
}
