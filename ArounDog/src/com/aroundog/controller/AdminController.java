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
import com.itbank.common.pager.Pager;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private FreeBoardService freeBoardService;
	@Autowired
	private ReportService reportService;
	
	//userservice-->사용
	//관리자 로그인 요청
	@RequestMapping(value="/admin/login", method=RequestMethod.GET)
	public String adminLogin(Admin admin, HttpServletRequest request) {
		Admin obj=adminService.loginCheck(admin);
		request.getSession().setAttribute("admin", obj);
		return "redirect:/admin/main";
	}
	
	//관리자 로그인 성공시 이동
	@RequestMapping(value="/admin/main", method=RequestMethod.GET)
	public String goMain(HttpServletRequest request) {
		//로그인 성공시 바로 회원페이지 가져오기위해 DB에서 회원 정보 긁어와야함
		List freeBoardList=freeBoardService.selectAll();
		//System.out.println(freeBoardList);
		request.setAttribute("freeBoardList", freeBoardList);
		return "admin/main/main";
	}
	
	//관리자 모드에서 리스트 불러오기
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String userList() {	
		return "admin/user/index";
	}
	
	//Report 관련 ---------------------------------------------#
	
	@RequestMapping(value="/reports",method=RequestMethod.GET)
	public ModelAndView reportList() {	
		List reportList=reportService.selectAll();//모델앤뷰로 리스트 반환하고.. jsp에서 리스트 받아서 목록 출력!!
		ModelAndView mav = new ModelAndView("admin/report/index");
		mav.addObject("reportList", reportList);
		return mav;
	} 
	
	@RequestMapping(value="/reports/{report_id}",method=RequestMethod.GET) 
	public ModelAndView select(@PathVariable("report_id") int report_id) {	
		ModelAndView mav = new ModelAndView("admin/report/detail"); 
		Report report =  reportService.select(report_id); 
		mav.addObject("report",report); 
		return mav;
	}
	
	@RequestMapping(value="/reportsimg/{report_id}",method=RequestMethod.GET)
	@ResponseBody
	public String selectImg(@PathVariable("report_id") int report_id) {
		List<ReportImg> imgList = reportService.selectImg(report_id);
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<imgList.size();i++) {
			ReportImg ri = imgList.get(i);
			JSONObject obj = new JSONObject();
			obj.put("img", ri.getImg());
			
			jsonArray.add(obj);
		}
		return jsonArray.toString();
		
	}
	
	@RequestMapping(value="/reports/check",method=RequestMethod.POST)
	public String update(@RequestParam("report_id") int report_id) {
		reportService.update(report_id);
		return "redirect:/reports";
	}
	
	/*
	 * @RequestMapping(value="reports/prev",method=RequestMethod.GET)
	 * 
	 * @ResponseBody public String prev(@RequestParam("currentPage") int
	 * currentPage) { System.out.println("받은 커런트값"+currentPage);
	 * System.out.println("설정전 커런트값"+pager.getCurrentPage());
	 * System.out.println("설정전 퍼스트"+pager.getFirstPage());
	 * pager.setCurrentPage(currentPage);
	 * System.out.println("설정후 커런트값"+pager.getCurrentPage());
	 * System.out.println("설정 후 퍼슼트값"+pager.getFirstPage());
	 * 
	 * return null; }
	 */
	
	//#---------------------------------------------Report 관련 끝
	
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
