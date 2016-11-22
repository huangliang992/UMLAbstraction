package com.hainu.cs.action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.ClassRank;
import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CountClassRankAction extends ActionSupport{
	private float[][] tranmatrix;
	private float[] rank;
	private String iterprocess;
	private String[] node;
	
	
	public String[] getNode() {
		return node;
	}

	public void setNode(String[] node) {
		this.node = node;
	}

	public float[][] getTranmatrix() {
		return tranmatrix;
	}
	
	public void setTranmatrix(float[][] tranmatrix) {
		this.tranmatrix = tranmatrix;
	}
	public float[] getRank() {
		return rank;
	}
	public void setRank(float[] rank) {
		this.rank = rank;
	}

	
	public String getIterprocess() {
		return iterprocess;
	}
	public void setIterprocess(String iterprocess) {
		this.iterprocess = iterprocess;
	}
	public String execute() throws Exception{
		//获取文件路径
		ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        Object filepath=session.get("filepath"); 
        String fp=filepath.toString();
        //对文件计算classrank
        ClassRank cr=new ClassRank();
        cr.init(fp);
        iterprocess=cr.getIterateprocess();
        rank=cr.getClassRank();
        tranmatrix=cr.getTransmatrix();
        node=cr.getNode();
        
        iterprocess=iterprocess.replaceAll("\n", "<br>");
        iterprocess=iterprocess.replaceAll(" ", "&nbsp");
        
       
        
        return "SUCCESS";
	}
}
