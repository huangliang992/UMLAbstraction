package com.hainu.cs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hainu.cs.bean.FileBean;
import com.hainu.cs.bean.User;
import com.hainu.cs.dao.FileInfoDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserFileSysAction extends ActionSupport {
	private FileBean fb = new FileBean();//文件信息描述类
	private String fileFileName;//上传的文件名
	private File file;//接收上传的文件信息
	private String fileresult;//包含DOM的table的结果显示
	private String dfileName;//下载的文件名
	private InputStream dfileDownload;//下载的流
	private String fy;//分页的分页栏
	private int fnumber;//分页码中的标签
	
	

	public int getFnumber() {
		return fnumber;
	}

	public void setFnumber(int fnumber) {
		this.fnumber = fnumber;
	}

	public String getFy() {
		return fy;
	}

	public void setFy(String fy) {
		this.fy = fy;
	}

	public InputStream getDfileDownload() {
		//下载文件
		System.out.println(this.dfileName);
		String fp="/WEB-INF/upload/"+this.dfileName;
		return ServletActionContext.getServletContext().getResourceAsStream(fp) ;
	}

	public void setDfileDownload(InputStream dfileDownload) {
		this.dfileDownload = dfileDownload;
	}

	public String getDfileName() {
		return dfileName;
	}

	public void setDfileName(String dfileName) {
		this.dfileName = dfileName;
	}

	public String getFileresult() {
		return fileresult;
	}

	public void setFileresult(String fileresult) {
		this.fileresult = fileresult;
	}

	public FileBean getFb() {
		return fb;
	}

	public void setFb(FileBean fb) {
		this.fb = fb;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String execute() throws IOException {

		String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		String filepath = path + File.separator + fileFileName;
		ActionContext actioncontext = ActionContext.getContext();
		Map session = actioncontext.getSession();
		User obj = (User) session.get("user");
		fb.setName(obj.getUsername());
		fb.setPassword(obj.getPassword());
		fb.setFilename(fileFileName);
		fb.setFilepath(filepath);
		System.out.println(fb.getFdescribe());
		System.out.println(fb.getType());
		System.out.println(filepath);
		System.out.println(fb.getUserid());
		FileInfoDao fid = new FileInfoDao();
		//将文件信息上传到mysql数据库中
		fid.insertFileInfo(fb);
		//将文件上传到服务器中
		FileInputStream is=new FileInputStream(file);
		FileOutputStream out = new FileOutputStream(filepath);  
        byte[]b = new byte[1024];  
        int len = 0;  
        while((len=is.read(b))>0){  
            out.write(b,0,len);  
        }  
        out.close();
		return "SUCCESS";
	}
	
	public String executed(){
		return "SUCCESS";
	}
	
	public String checkFile() {
		FileInfoDao ff = new FileInfoDao();
		ActionContext actioncontext = ActionContext.getContext();
		Map session = actioncontext.getSession();
		User obj = (User) session.get("user");
		// 将该用户上传的历史以列表的形式输出出来
		List l = ff.checkList(obj.getUsername(), obj.getPassword());
		fileresult = "";
		for (int i = 0; i < 1; i++) {
			FileBean u = (FileBean) l.get(i);
			fileresult = fileresult + "<tr class=\"warning\"><td>" + u.getFilename() + "</td><td>" + u.getType()
					+ "</td><td>" + u.getFdescribe() + "</td><td>" + u.getFilepath() + "</td><td>"
					+ "<a href=\"DownloadUfile.action?dfileName=" + u.getFilename() + "\">" + "download</a></td></tr>";
		}
		fy = "";
		fy = fy + "<div class=\"row\">" + "<div class=\"col-md-12\">" + "<ul class=\"pagination\">";
		//条目小于5条的话
		if(l.size()<5){
			for(int i=0;i<l.size()+2;i++){
				if (i == 0) {
					fy = fy + "<li><a href=\"#\">Prev</a></li>";
				} else if (i == l.size()+1) {
					int j = i + 1;
					fy = fy + "<li><a href=\"FY.action?fnumber=" + j + "\">Next</a></li>";
				} else {
					// 点击分页栏的页码标签，会将页码传递给后台，如果页码大于3重新生成分页栏
					fy = fy + "<li><a href=\"FY.action?fnumber=" + i + "\">" + i + "</a></li>";
				}
			}
			fy = fy + "</ul></div></div>";
		}
		//如果条目小于5条的话
		if(l.size()>=5){
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				fy = fy + "<li><a href=\"#\">Prev</a></li>";
			} else if (i == 6) {
				int j = i + 1;
				fy = fy + "<li><a href=\"FY.action?fnumber=" + j + "\">Next</a></li>";
			} else {
				// 点击分页栏的页码标签，会将页码传递给后台，如果页码大于3重新生成分页栏
				fy = fy + "<li><a href=\"FY.action?fnumber=" + i + "\">" + i + "</a></li>";
			}
		}
		fy = fy + "</ul></div></div>";
		}
		return "SUCCESS";
	}
	
	public String fYe() {

		FileInfoDao ff = new FileInfoDao();
		ActionContext actioncontext = ActionContext.getContext();
		Map session = actioncontext.getSession();
		User obj = (User) session.get("user");
		// 将该用户上传的历史以列表的形式输出出来
		List l = ff.checkList(obj.getUsername(), obj.getPassword());
		fileresult = "";
		if (fnumber < l.size() && 0 <= fnumber) {
			for (int i = fnumber; i < fnumber + 1; i++) {
				FileBean u = (FileBean) l.get(i);
				fileresult = fileresult + "<tr class=\"warning\"><td>" + u.getFilename() + "</td><td>" + u.getType()
						+ "</td><td>" + u.getFdescribe() + "</td><td>" + u.getFilepath() + "</td><td>"
						+ "<a href=\"DownloadUfile.action?dfileName=" + u.getFilename() + "\">"
						+ "download</a></td></tr>";
			}

			// 重新生成分页栏
			fy = "";
			fy = fy + "<div class=\"row\">" + "<div class=\"col-md-12\">" + "<ul class=\"pagination\">";

			// 点击分页栏的页码标签，会将页码传递给后台，如果页码大于3重新生成分页栏
			int n1 = fnumber - 1;
			int n2 = fnumber + 1;
			//pre标签码
			if (fnumber >= 0) {
				fy = fy + "<li><a href=\"FY.action?fnumber=" + n1 + "\">Prev</a></li>";
			} else {
				fy = fy + "<li><a href=\"#\">Prev</a></li>";
			}
			//中间的标签码
			if (fnumber > 3&&fnumber<l.size()-2) {
				for (int j = fnumber - 2; j < fnumber + 3; j++) {
					fy = fy + "<li><a href=\"FY.action?fnumber=" + j + "\">" + j + "</a></li>";
				}
			} else {
				for (int j = 1; j < 7; j++) {
					fy = fy + "<li><a href=\"FY.action?fnumber=" + j + "\">" + j + "</a></li>";
				}
			}
			//next标签码
			if(fnumber<l.size()-1){
			fy = fy + "<li><a href=\"FY.action?fnumber=" + n2 + "\">Next</a></li>";
			}else{
				fy = fy + "<li><a href=\"#\">Next</a></li>";
			}
		}
		fy = fy + "</ul></div></div>";

		return "SUCCESS";
	}
	}

