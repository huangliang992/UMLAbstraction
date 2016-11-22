package com.hainu.cs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport{
	private File upload;
	private String uploadFileName;//XXXFileName is used to get the name of the file
	private String uploadContentType;// XXXContentType is used to get the type of the file
	private String statu="";
	
	
	private String classes;//类信息的JSON字符串
	private String ass;//关联的JSON字符串
	private String agg;//聚集关系的JSON字符串
	private String gl;//泛化关系的JSON字符串
	private String dp;//依赖关系的JSON字符串
	private String cp;
	
	
	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getAss() {
		return ass;
	}


	public void setAss(String ass) {
		this.ass = ass;
	}


	public String getAgg() {
		return agg;
	}


	public void setAgg(String agg) {
		this.agg = agg;
	}


	public String getGl() {
		return gl;
	}


	public void setGl(String gl) {
		this.gl = gl;
	}


	public String getDp() {
		return dp;
	}


	public void setDp(String dp) {
		this.dp = dp;
	}


	public String getClasses() {
		return classes;
	}


	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getStatu() {
		return statu;
	}


	public void setStatu(String statu) {
		this.statu = statu;
	}


	public String execute() throws Exception{
		//window 路径
		String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");  
		//
        String filename = path+File.separator+uploadFileName; 
        System.out.println(uploadFileName);
        System.out.println(filename);
        
        //session test
        ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        session.put("filepath", filename);
        session.put("path",path);
        
       if(upload!=null){
    	   int loc=filename.lastIndexOf('.');
    	   String houzhui="";
    	   for(int i=loc+1;i<filename.length();i++){
    		   houzhui=houzhui+filename.charAt(i);
    	   }
    	   if(houzhui.equals("uml")){
        FileInputStream in = new FileInputStream(upload);  
        FileOutputStream out = new FileOutputStream(filename);  
        byte[]b = new byte[1024];  
        int len = 0;  
        while((len=in.read(b))>0){  
            out.write(b,0,len);  
        }  
        out.close();  
        //下面部分拷贝到所有action中
        XMLManagerFul xml=new XMLManagerFul();
        xml.init(filename);
        
        JSONObject cjb=new JSONObject();//类的JSON对象
        JSONObject asjb=new JSONObject();//关联
        JSONObject agjb=new JSONObject();
        JSONObject gljb=new JSONObject();
        JSONObject dpjb=new JSONObject();
        JSONObject cpjb=new JSONObject();
        
        HashMap<String,ClassFigure> cs=xml.getCls();
        for(Map.Entry<String, ClassFigure>entry:cs.entrySet()){
        	ClassFigure value=entry.getValue();
        	//System.out.println(value.getObjname());
        	JSONObject obj=new JSONObject();
        	obj.put("id", value.getId());
        	obj.put("name", value.getName());
        	obj.put("x", value.getX());
        	obj.put("y", value.getY());
        	obj.put("attribute", value.getAttr());
        	obj.put("operation", value.getOper());
        	//System.out.println(obj.toString());
        	cjb.put(value.getName(), obj);
        	classes=cjb.toString();
        }
        
        HashMap<String,ASFigure> as=xml.getAS();
        int k1=0;
        for(Map.Entry<String, ASFigure>entry:as.entrySet()){
        	k1++;
        	ASFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	asjb.put("key"+k1,obj);
        	ass=asjb.toString();
        }
        
        
        HashMap<String,AGFigure> ag=xml.getAG();
        int k2=0;
        for(Map.Entry<String, AGFigure>entry:ag.entrySet()){
        	k2++;
        	AGFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	agjb.put("key"+k2,obj);
        	agg=agjb.toString();
        }
        
        
        HashMap<String,GLFigure> gll=xml.getGL();
        int k3=0;
        for(Map.Entry<String, GLFigure>entry:gll.entrySet()){
        	k3++;
        	GLFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	gljb.put("key"+k3,obj);
        	gl=gljb.toString();
        }
        
        HashMap<String,DPFigure> dep=xml.getDP();
        int k4=0;
        for(Map.Entry<String, DPFigure>entry:dep.entrySet()){
        	k4++;
        	DPFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	dpjb.put("key"+k4,obj);
        	dp=dpjb.toString();
        }
        
        HashMap<String,CPFigure> cop=xml.getCP();
        int k5=0;
        for(Map.Entry<String, CPFigure>entry:cop.entrySet()){
        	k5++;
        	CPFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	cpjb.put("key"+k5,obj);
        	cp=cpjb.toString();
        }
        
        System.out.println(classes.toString());
        System.out.println(ass);
        System.out.println(agg);
        System.out.println(gl);
        System.out.println(dp);
        System.out.println(cp);
        session.put("classes", classes);
        session.put("ass", ass);
        session.put("agg", agg);
        session.put("gl", gl);
        session.put("dp", dp);
        session.put("cp", cp);
        statu="SUCCESS";
        return "SUCCESS";
    	   }
    	   else {
    		   statu="file is not right";
    		   return "False1";
    	   }
       }else {
    	   statu="have'n upload file";
    	   return "False";
    	   }
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	
	
}
