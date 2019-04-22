package com.aroundog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aroundog.common.exception.EditFailException;
import com.aroundog.common.exception.ReportFailException;
import com.aroundog.model.domain.Report;
import com.aroundog.model.domain.ReportImg;
import com.aroundog.model.repository.ReportDAO;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	@Qualifier("mybatisReportDAO")
	private ReportDAO reportDAO;

	@Override
	public void insert(Report report) throws ReportFailException {
		int result = reportDAO.insert(report);
		if (result == 0) {
			throw new ReportFailException("제보 실패!!");
		}
	}

	@Override
	public void insertImg(ReportImg reportImg) throws ReportFailException {
		int result = reportDAO.insertImg(reportImg);
		if (result == 0) {
			throw new ReportFailException("제보 실패!!");
		}

	}

	@Override
	public List selectAll() {
		return reportDAO.selectAll();
	}

	@Override
	public Report select(int report_id) {
		return reportDAO.select(report_id);
	}

	@Override
	public List selectImg(int report_id) {
		return reportDAO.selectImg(report_id);
	}

	@Override
	public void update(int report_id) {
		int result = reportDAO.update(report_id);
		if(result ==0) {
			throw new EditFailException("확인 실패");
		}
	}
}