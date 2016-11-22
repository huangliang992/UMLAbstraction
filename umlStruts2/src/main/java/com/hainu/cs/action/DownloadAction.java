package com.hainu.cs.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hainu.cs.util.ReadDirectory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{
		

		private String fileName;
		private InputStream fileDownload;
		
		private String fileListJSON;
		
		private String result;
		

		
		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getFileListJSON() {
			return fileListJSON;
		}

		public void setFileListJSON(String fileListJSON) {
			this.fileListJSON = fileListJSON;
		}

		public InputStream getFileDownload() {
			//this.fileName = "example.uml" ;
			
			System.out.println(this.fileName);
			String fp="/WEB-INF/upload/"+this.fileName;
			   return ServletActionContext.getServletContext().getResourceAsStream(fp) ;
		}

		public void setFileDownload(InputStream fileDownload) {
			this.fileDownload = fileDownload;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
		@Override
		public String execute() throws Exception {
			String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
			ReadDirectory rd=new ReadDirectory();
			rd.readFile(path);
			fileListJSON=rd.getFileStr();
			System.out.println(fileListJSON);
			result="<table class=\"table\" border=\"1\">"+"<tr><td>File Name</td><td>File Path</td>"
					+ "<td>Download File</td></tr>";
			HashMap<String,String> l=rd.getList();
			for(Map.Entry<String, String>entry:l.entrySet()){
				String key=entry.getKey();
				String value=entry.getValue();
				//<a href="Download.action?fileName=<%="test1.uml"%>">test</a>
				String down="";
				System.out.println(key);
				down=down+"<a href=\"Download.action?fileName="+key+"\"> Download</a>";
				System.out.println(down);
				result=result+"<tr><td>"+key+"</td><td>"+value+"</td><td>"+down+"</td></tr>";
			}
			result=result+"</table>";
			return "SUCCESS";
		}

	}


