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
			System.out.println("������ ù ���������̸�"+filename);
			File uploadFile = null;
			try {
				uploadFile = new File(realPath + "/" + filename);
				System.out.println(i+"��°  transferTo �� ������"+myFile[i].getName());
				myFile[i].transferTo(new File(realPath + "/" + filename));
				System.out.println(i+"��°  transferTo �� ������"+myFile[i].getName());
				System.out.println("fileManager.reNameByDate �� ���� �̸�"+filename);
				System.out.println("fileManager.reNameByDate �� uploadFile��"+uploadFile.getName()+" , realPath��"+realPath);
				filename = fileManager.reNameByDate(uploadFile, realPath);
				System.out.println("fileManager.reNameByDate �� uploadFile��"+uploadFile.getName()+" , realPath��"+realPath);
				System.out.println("fileManager.reNameByDate �� ���� �̸�"+filename);
				if (filename != null) {
					filenameList[i] = filename;
					System.out.println(i+"���� �����̸� "+filename);
					System.out.println("=========================");

			} 
			}catch (IllegalStateException | IOException e) {
				System.out.println("�� ����ڴ� ������ ������� �ʾҽ��ϴ�");
			}
		}
		return filenameList;
	}
}
