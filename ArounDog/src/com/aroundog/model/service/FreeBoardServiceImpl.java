package com.aroundog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aroundog.model.repository.FreeBoardDAO;
@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	@Autowired
	private FreeBoardDAO freeBoardDAO;
	
	//��� �����Խ��� ��������
	public List selectAll() {
		List freeBoardList=freeBoardDAO.selectAll();
		return freeBoardList;
	}

}
