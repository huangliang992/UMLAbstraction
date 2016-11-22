package com.hainu.cs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.AbstractPaths;
import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AbstractPathAction extends ActionSupport{
	private String origin;
	private String dest;
	private String absresult="";
	
	
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getAbsresult() {
		return absresult;
	}

	public void setAbsresult(String absresult) {
		this.absresult = absresult;
	}

	
	
	public String execute() throws Exception{
		ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        Object filepath=session.get("filepath"); 
        String fp=filepath.toString();
        AbstractPaths ap=new AbstractPaths();
        ap.init(fp, origin, dest);
        ArrayList<String> a=ap.getAbstractResults();
        for(int i=0;i<a.size();i++){
        	absresult=absresult+a.get(i)+"<br>";
        }
        absresult=absresult.replaceAll("\n", "<br>");
       
        return "SUCCESS";
	}
}
