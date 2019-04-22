package com.aroundog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aroundog.model.service.FreeBoardService;

@Controller
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;
	
	public String selectAll(HttpServletRequest request) {
		List freeBoardList=freeBoardService.selectAll();
		request.setAttribute("freeBoardList", freeBoardList);
		return "";
	}

	 
	
}
