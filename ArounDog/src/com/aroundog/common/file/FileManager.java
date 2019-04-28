package com.aroundog.common.file;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileManager {
	private ReNameSercurity rs = new ReNameSercurity();
   public String getExt(String path) {
      int lastIndex = path.lastIndexOf(".");
      return path.substring(lastIndex+1, path.length());
   }

   public boolean save() {   
      return false;
   }

   public String reNameByDate(File ori,String dir) {
      long time = System.currentTimeMillis();
      String filename = time+"."+getExt(ori.getName());
      boolean result = ori.renameTo(new File(dir+"/"+filename));		
		/*
		 * if(!result) { filename = null; }
		 * 
		 */
      return filename;
   }
   
   public String reNameByHash(File ori,String dir) {
	   String hash = rs.textToHash(ori.getName());
	   String filename = hash+"."+getExt(ori.getName());
	   boolean result = ori.renameTo(new File(dir+"/"+filename));		
	   return filename;
	   }
}