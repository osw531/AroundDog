package com.aroundog.common.file;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileManager {
   //���ϸ� �� Ȯ���� �����Ͽ� ��ȯ�ϴ� �޼���
   public String getExt(String path) {
      //���� ���� .�� ���� ����. ����.jpg
      int lastIndex = path.lastIndexOf(".");
      return path.substring(lastIndex+1, path.length());
   }
   
   //������ ��ο� ������ �����ϴ� �޼���!!
   public boolean save() {   
      return false;
   }
   
   //���ϸ� �ٲٱ� : ������ ���ο� ���ϸ��� ��¥�� �̿��Ѵ�.
   public String reNameByDate(File ori,String dir) {
      long time = System.currentTimeMillis();
      String filename = time+"."+getExt(ori.getName());
		/* System.out.println("###���ϸŴ������� ���� renameTo �� �����̸���"+filename); */
      boolean result = ori.renameTo(new File(dir+"/"+filename));
		
		  System.out.println("result ��������ϱ�##############");
		  System.out.println(ori.renameTo(new File(dir+"/"+filename)));
		  System.out.println("###���ϸŴ������� ���� renameTo �� �����̸���"+filename);
		  System.out.println("###���ϸŴ��� �ȿ����� result�����"+result);
		
		/*
		 * if(!result) { filename = null; }
		 * 
		 */
      return filename;
   }
   
   /*
    * public static void main(String[] args) { FileManager fm = new FileManager();
    * System.out.println(fm.getExt("��������.��.���Ͼ˰�����.JPG")); }
    */
}