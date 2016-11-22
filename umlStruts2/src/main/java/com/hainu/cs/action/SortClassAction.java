package com.hainu.cs.action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.SortClass;
import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SortClassAction extends ActionSupport{
	private float[] srank;
	private String[] node;
	
	
	public String[] getNode() {
		return node;
	}



	public void setNode(String[] node) {
		this.node = node;
	}



	public float[] getSrank() {
		return srank;
	}



	public void setSrank(float[] srank) {
		this.srank = srank;
	}



	public String execute() throws Exception{
		ActionContext actionContext=ActionContext.getContext();
		Map session=actionContext.getSession();
		Object filepath=session.get("filepath");
		String fp=filepath.toString();
		SortClass sc=new SortClass();
		sc.init(fp);
		srank=sc.getRankSorted();
		node=sc.getNodeSorted();
		
		return "SUCCESS";
	}
}
