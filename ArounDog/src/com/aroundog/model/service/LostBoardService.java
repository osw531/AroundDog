package com.aroundog.model.service;

import java.util.List;

import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;

public interface LostBoardService {
   public void insert(LostBoard lostBoard);
   public void insertImg(LostBoardImg lostBoardImg);
   public List selectAll();
}