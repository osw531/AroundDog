package com.aroundog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aroundog.model.repository.TypeDAO;
@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeDAO typeDAO;
	@Override
	public List selectAll() {
		return typeDAO.selectAll();
	}
}
