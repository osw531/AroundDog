package com.aroundog.model.repository;

import java.util.List;

import com.aroundog.model.domain.Type;

public interface TypeDAO {
	public Type select(String info);
	public List selectAll();
}
