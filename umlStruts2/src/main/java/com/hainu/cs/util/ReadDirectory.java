package com.hainu.cs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

public class ReadDirectory {
	
	JSONObject fileList=new JSONObject();
	String fileStr;
	HashMap<String,String> list=new HashMap<String,String>();
	
	
	 public HashMap<String, String> getList() {
		return list;
	}

	public void setList(HashMap<String, String> list) {
		this.list = list;
	}

	public String getFileStr() {
		return fileStr;
	}

	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}

	public JSONObject getFileList() {
		return fileList;
	}

	public void setFileList(JSONObject fileList) {
		this.fileList = fileList;
	}

	public void readFile(String filepath) throws FileNotFoundException, IOException {
         try {

                 File file = new File(filepath);
                 if (!file.isDirectory()) {
                	 	fileList.put(file.getName(), file.getAbsolutePath());
                	 	list.put(file.getName(), file.getAbsolutePath());
                 } else if (file.isDirectory()) {
                        // System.out.println("文件夹");
                         String[] filelist = file.list();
                         for (int i = 0; i < filelist.length; i++) {
                                 File readfile = new File(filepath + File.separator + filelist[i]);
                                 if (!readfile.isDirectory()) {
                                	//System.out.println(readfile.getName());
                                	 //System.out.println(readfile.getAbsolutePath());
                                	 fileList.put(readfile.getName(), readfile.getAbsolutePath());
                                	 list.put(readfile.getName(), readfile.getAbsolutePath());

                                 } else if (readfile.isDirectory()) {
                                         readFile(filepath + File.separator + filelist[i]);
                                 }
                         }

                 }
                 fileStr=fileList.toString();
                 //System.out.println(fileStr);
         } catch (FileNotFoundException e) {
                 System.out.println("readfile()   Exception:" + e.getMessage());
         }
 }

 /**
  * 删除某个文件夹下的所有文件夹和文件
  */
 
 
 /*public static boolean deletefile(String delpath)
                 throws FileNotFoundException, IOException {
         try {

                 File file = new File(delpath);
                 if (!file.isDirectory()) {
                         System.out.println("1");
                         file.delete();
                 } else if (file.isDirectory()) {
                         System.out.println("2");
                         String[] filelist = file.list();
                         for (int i = 0; i < filelist.length; i++) {
                                 File delfile = new File(delpath + "\\" + filelist[i]);
                                 if (!delfile.isDirectory()) {
                                         System.out.println("path=" + delfile.getPath());
                                         System.out.println("absolutepath="
                                                         + delfile.getAbsolutePath());
                                         System.out.println("name=" + delfile.getName());
                                         delfile.delete();
                                         System.out.println("删除文件成功");
                                 } else if (delfile.isDirectory()) {
                                         deletefile(delpath + "\\" + filelist[i]);
                                 }
                         }
                         file.delete();

                 }

         } catch (FileNotFoundException e) {
                 System.out.println("deletefile()   Exception:" + e.getMessage());
         }
         return true;
 }*/
 
}
