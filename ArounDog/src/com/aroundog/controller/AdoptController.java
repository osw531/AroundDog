package com.aroundog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aroundog.common.exception.AdoptRegistFailException;
import com.aroundog.model.domain.Adopt;
import com.aroundog.model.service.AdoptService;

@Controller
public class AdoptController {

	@Autowired
	private AdoptService adoptService;
	
	@RequestMapping(value="/user/adoption", method=RequestMethod.POST)
	public String adoptRegist(Adopt adopt) {
		adoptService.insert(adopt);
		return "redirect:/user/detail.jsp";
	}
	
	@ExceptionHandler(AdoptRegistFailException.class)
	@ResponseBody
	public ModelAndView adoptRegistFail(AdoptRegistFailException e) {
		ModelAndView mav = new ModelAndView("user/adopt");
		mav.addObject("err", e);
		return null;
	}
}