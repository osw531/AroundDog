package com.aroundog.common.file;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aroundog.model.domain.Report;
import com.aroundog.model.domain.ReportImg;

public class ReportImgUploader {
	private FileManager fileManager = new FileManager();

	public String[] returnFilename(MultipartFile[] myFile, Report report, ReportImg reportImg, String realPath) {
		String[] filenameList = new String[myFile.length];
		for (int i = 0; i < myFile.length; i++) {
			String filename = myFile[i].getOriginalFilename();
			System.out.println("실행후 첫 받은원본이름"+filename);
			File uploadFile = null;
			try {
				uploadFile = new File(realPath + "/" + filename);
				System.out.println(i+"번째  transferTo 전 파일은"+myFile[i].getName());
				myFile[i].transferTo(new File(realPath + "/" + filename));
				System.out.println(i+"번째  transferTo 후 파일은"+myFile[i].getName());
				System.out.println("fileManager.reNameByDate 전 파일 이름"+filename);
				System.out.println("fileManager.reNameByDate 전 uploadFile은"+uploadFile.getName()+" , realPath는"+realPath);
				filename = fileManager.reNameByDate(uploadFile, realPath);
				System.out.println("fileManager.reNameByDate 후 uploadFile은"+uploadFile.getName()+" , realPath는"+realPath);
				System.out.println("fileManager.reNameByDate 후 파일 이름"+filename);
				if (filename != null) {
					filenameList[i] = filename;
					System.out.println(i+"번쨰 파일이름 "+filename);
					System.out.println("=========================");

			} 
			}catch (IllegalStateException | IOException e) {
				System.out.println("이 사용자는 파일을 등록하지 않았습니다");
			}
		}
		return filenameList;
	}
}
