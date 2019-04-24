package com.aroundog.model.repository;

import java.util.List;

import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;

public interface LostBoardDAO {
	public int insert(LostBoard lostBoard);
	public int insertImg(LostBoardImg lostBoardImg);
	public List selectAll();
	public LostBoard select(int lostboard_id);
	public List selectImg(int lostboard_id);
	public int update(int lostboard_id);
}
