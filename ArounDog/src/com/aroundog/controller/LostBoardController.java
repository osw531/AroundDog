package com.aroundog.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aroundog.model.domain.Type;
import com.aroundog.model.service.TypeService;

@RestController
public class LostBoardController {
	@Autowired
	private TypeService typeService;
	
	@RequestMapping(value="/rest/lostboard/types",method = RequestMethod.GET)
	public List selectAllType() {
		System.out.println("¤¾¤·");
		//List<Type> typeList =
		/*
		 * JSONArray jsonArray = new JSONArray(); for(int i = 0;i<typeList.size();i++) {
		 * Type type = typeList.get(i); JSONObject obj = new JSONObject();
		 * obj.put("info", type.getInfo());
		 * 
		 * jsonArray.add(obj); }
		 */
		//return jsonArray.toString();
		return typeService.selectAll();
	}
}
