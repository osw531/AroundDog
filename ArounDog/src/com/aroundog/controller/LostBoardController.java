package com.aroundog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aroundog.common.file.FileManager;
import com.aroundog.model.domain.LostBoard;
import com.aroundog.model.domain.LostBoardImg;
import com.aroundog.model.domain.Report;
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
	   public String insert(LostBoard lostBoard,LostBoardImg lostBoardImg,Type type,HttpServletRequest request) {    
		  lostBoard.setType(type);
	      MultipartFile[] myFile = lostBoard.getMyFile();
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
	            System.out.println("이 사용자는 파일을 등록하지 않았습니다");
	         }
	      }
	      return "redirect:/user/lostboard/lostboardlist";
	   }
	
	   
	   @RequestMapping(value="/user/lostboard/lostboardlist",method = RequestMethod.GET)
	   public ModelAndView goIndex() {
		   List<LostBoard> lostBoardList = lostBoardService.selectAll();
		   ModelAndView mav = new ModelAndView("user/lostboard/lostboardlist");
		   mav.addObject("lostBoardList",lostBoardList);
		   return mav;
	   }
	   
		@RequestMapping(value="/user/lostboard/lostboardlist/{lostboard_id}",method=RequestMethod.GET) 
		public ModelAndView select(@PathVariable("lostboard_id") int lostboard_id) {	
			ModelAndView mav = new ModelAndView("user/lostboard/lostboarddetail"); 
			LostBoard lostBoard =  lostBoardService.select(lostboard_id);
			lostBoardService.update(lostboard_id);
			List<LostBoardImg> imgList = lostBoardService.selectImg(lostboard_id);
			mav.addObject("lostBoard",lostBoard); 
			mav.addObject("imgList", imgList);
			return mav;
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
