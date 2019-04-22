package com.aroundog.model.service;

import java.util.List;

import com.aroundog.model.domain.Report;
import com.aroundog.model.domain.ReportImg;

public interface ReportService {
   public void insert(Report report);
   public void insertImg(ReportImg reportImg);
   public List selectAll();
   public Report select(int report_id);
   public List selectImg(int report_id);
   public void update(int report_id);
}