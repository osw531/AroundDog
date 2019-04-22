package com.aroundog.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aroundog.model.domain.Admin;
import com.aroundog.model.domain.Report;
import com.aroundog.model.domain.ReportImg;
import com.aroundog.model.service.AdminService;
import com.aroundog.model.service.FreeBoardService;
import com.aroundog.model.service.ReportService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private FreeBoardService freeBoardService;
	@Autowired
	private ReportService reportService;
	//userservice-->���
	//������ �α��� ��û
	@RequestMapping(value="/admin/login", method=RequestMethod.GET)
	public String adminLogin(Admin admin, HttpServletRequest request) {
		Admin obj=adminService.loginCheck(admin);
		request.getSession().setAttribute("admin", obj);
		return "redirect:/admin/main";
	}
	
	//������ �α��� ������ �̵�
	@RequestMapping(value="/admin/main", method=RequestMethod.GET)
	public String goMain(HttpServletRequest request) {
		//�α��� ������ �ٷ� ȸ�������� ������������ DB���� ȸ�� ���� �ܾ�;���
		List freeBoardList=freeBoardService.selectAll();
		//System.out.println(freeBoardList);
		request.setAttribute("freeBoardList", freeBoardList);
		return "admin/main/main";
	}
	
	//������ ��忡�� ����Ʈ �ҷ�����
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String userList() {	
		return "admin/user/index";
	}
	
	//Report ���� ---------------------------------------------#
	
	@RequestMapping(value="/reports",method=RequestMethod.GET)
	public ModelAndView reportList() {	
		List reportList=reportService.selectAll();//�𵨾غ�� ����Ʈ ��ȯ�ϰ�.. jsp���� ����Ʈ �޾Ƽ� ��� ���!!
		System.out.println("Report���̺��� �������"+reportList.size());
		ModelAndView mav = new ModelAndView("admin/report/index");
		mav.addObject("reportList", reportList);
		return mav;
	} 
	
	@RequestMapping(value="/reports/{report_id}",method=RequestMethod.GET) 
	public ModelAndView select(@PathVariable("report_id") int report_id) {
		System.out.println("admin/report�� detail ������!!");		
		  ModelAndView mav = new ModelAndView("admin/report/detail"); 
		  Report report =  reportService.select(report_id); 
		  System.out.println("report_id��"+report_id);
	  
		  mav.addObject("report",report);
		 
		return mav;
	}
	
	@RequestMapping(value="/reportsimg/{report_id}",method=RequestMethod.GET)
	@ResponseBody
	public String selectImg(@PathVariable("report_id") int report_id) {
		System.out.println("÷������ ����?"+report_id);
		List<ReportImg> imgList = reportService.selectImg(report_id);
		System.out.println("�̹��� ����Ʈ ũ��� " +imgList.size());
		
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<imgList.size();i++) {
			ReportImg ri = imgList.get(i);
			JSONObject obj = new JSONObject();
			obj.put("img", ri.getImg());
			
			jsonArray.add(obj);
		}
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
		
	}
	
	@RequestMapping(value="/reports/check",method=RequestMethod.POST)
	public String update(@RequestParam("report_id") int report_id) {
		System.out.println("������Ʈ �ҰŴ�? �ѱ� ���̵��"+report_id);
		reportService.update(report_id);
		System.out.println("������Ʈ ����");
		//return "redirect:/admin/report/index.jsp";
		return "redirect:/reports";
	}
	
	//#---------------------------------------------Report ���� ��
	
	@RequestMapping(value="/adopts",method=RequestMethod.GET)
	public String adoptList() {	
		return "admin/adopt/index";
	}
	@RequestMapping(value="/freeboards",method=RequestMethod.GET)
	public String freeBoardList() {	
		List freeBoardList=freeBoardService.selectAll();
		return "admin/freeboard/index";
	}
	@RequestMapping(value="/adoptmanagers",method=RequestMethod.GET)
	public String adoptManagerList() {	
		return "admin/adoptmanager/index";
	}
	

	
}
