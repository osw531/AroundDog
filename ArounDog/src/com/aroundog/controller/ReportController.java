package com.aroundog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aroundog.common.file.FileManager;
import com.aroundog.model.domain.Report;
import com.aroundog.model.domain.ReportImg;
import com.aroundog.model.service.ReportService;
 

@Controller
public class ReportController {
   @Autowired
   private ReportService reportService;
   @Autowired
   private FileManager fileManager;

   @RequestMapping(value = "/user/report", method = RequestMethod.POST)
   public String report(Report report,ReportImg reportImg, HttpServletRequest request) {
      // System.out.println("report ½ÇÇà!");
      MultipartFile[] myFile = report.getMyFile();
      reportService.insert(report);
      for (int i = 0; i < myFile.length; i++) {
         String filename = myFile[i].getOriginalFilename();
         String realPath = request.getServletContext().getRealPath("/data");

         File uploadFile = null;
         

         try {
            uploadFile = new File(realPath + "/" + filename);
            String ext = fileManager.getExt(filename);
            myFile[i].transferTo(new File(realPath + "/" + filename));

            filename = fileManager.reNameByDate(uploadFile, realPath);
            if (filename != null) {
               reportImg.setImg(filename);   
               reportImg.setReport(report);
               reportService.insertImg(reportImg);
               
            }
         } catch (IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return "user/index";
   }
}