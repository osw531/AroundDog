package com.aroundog.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;

import com.aroundog.common.file.FileManager;
import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;

import com.aroundog.model.domain.Type;
import com.aroundog.model.service.LostBoardService;
import com.aroundog.model.service.TypeService;

@Controller
public class LostBoardController {
	@Autowired
	private TypeService typeService;
	@Autowired
	private LostBoardService lostBoardService;

	   @Autowired
	   private FileManager fileManager;

	   @RequestMapping(value ="/user/lostboard", method = RequestMethod.POST)
	   public String insert(LostBoard lostBoard,LostBoardImg lostBoardImg, String info,HttpServletRequest request) {
	      System.out.println("로스트보드 삽입 실행!");
	      
	      Type type = typeService.select(info);
	      lostBoard.setType(type);
	      
	      MultipartFile[] myFile = lostBoard.getMyfile();
	      lostBoardService.insert(lostBoard);
	      for (int i = 0; i < myFile.length; i++) {
	         String filename = myFile[i].getOriginalFilename();
	         String realPath = request.getServletContext().getRealPath("/data");

	         File uploadFile = null;
	         

	         try {
	            uploadFile = new File(realPath + "/" + filename);
	            myFile[i].transferTo(new File(realPath + "/" + filename));

	            filename = fileManager.reNameByDate(uploadFile, realPath);
	            if (filename != null) {
	             lostBoardImg.setImg(filename);   
	             lostBoardImg.setLostboard(lostBoard);
	             lostBoardService.insertImg(lostBoardImg);
	               
	            }
	         } catch (IllegalStateException | IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
	      System.out.println("실행끝");
	      return null;
	   }
	
	/*
	 * @RequestMapping(value="/rest/lostboard/types",method = RequestMethod.GET)
	 * public List selectAllType() { System.out.println("ㅎㅇ"); //List<Type> typeList
	 * =
	 * 
	 * JSONArray jsonArray = new JSONArray(); for(int i = 0;i<typeList.size();i++) {
	 * Type type = typeList.get(i); JSONObject obj = new JSONObject();
	 * obj.put("info", type.getInfo());
	 * 
	 * jsonArray.add(obj); }
	 * 
	 * //return jsonArray.toString(); return typeService.selectAll(); }
	 */
}
