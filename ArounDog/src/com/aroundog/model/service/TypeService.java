package com.aroundog.model.service;

import java.util.List;

import com.aroundog.model.domain.Type;

public interface TypeService {
	public Type select(String info);
	public List selectAll();
}
