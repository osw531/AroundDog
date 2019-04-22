package com.aroundog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aroundog.common.exception.AdoptRegistFailException;
import com.aroundog.model.domain.Adopt;
import com.aroundog.model.repository.AdoptDAO;

@Service
public class AdoptServiceImpl implements AdoptService{
	
	@Autowired 
	@Qualifier("mybatisAdoptDAO")
	private AdoptDAO adoptDAO;

	public void insert(Adopt adopt) throws AdoptRegistFailException{
		int result= adoptDAO.insert(adopt);
		if(result==0) {
			throw new AdoptRegistFailException("입양신청 양식이 보내지지 않았습니다");
		}
	}
	
	
}