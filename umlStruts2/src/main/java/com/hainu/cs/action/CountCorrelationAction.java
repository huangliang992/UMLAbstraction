package com.hainu.cs.action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.CountCorrelation;
import com.hainu.cs.circulationchecking.controler.CreateGraph;
import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CountCorrelationAction extends ActionSupport{
	private String corresult;
	private String relresult="";
	private String[][] rel;
	private float[][] corr;
	private String[] node;
	
	
	public String[] getNode() {
		return node;
	}



	public void setNode(String[] node) {
		this.node = node;
	}



	public String[][] getRel() {
		return rel;
	}



	public void setRel(String[][] rel) {
		this.rel = rel;
	}



	public float[][] getCorr() {
		return corr;
	}



	public void setCorr(float[][] corr) {
		this.corr = corr;
	}



	public String getCorresult() {
		return corresult;
	}



	public void setCorresult(String corresult) {
		this.corresult = corresult;
	}



	public String getRelresult() {
		return relresult;
	}



	public void setRelresult(String relresult) {
		this.relresult = relresult;
	}



	public String execute() throws Exception{
		ActionContext actioncontext=ActionContext.getContext();
		Map session=actioncontext.getSession();
		Object filepath=session.get("filepath"); 
        String fp=filepath.toString();

        CreateGraph c=new CreateGraph();
		c.initGraph(fp);
		CountCorrelation co=new CountCorrelation();
		co.init(c.getNode(), c.getEdge());
		
		rel=co.getRelationType();
		corr=co.getCorrelation();
		node=c.getNode();
		
		String[][] s=co.getRelationType();
		float [][] f=co.getCorrelation();
		
		for (int i = 0; i < s.length; i++) {
			System.out.print("  " + c.getNode()[i] + " ");
			relresult=relresult+"  " + c.getNode()[i] + " ";
		}
		System.out.println();
		relresult=relresult+"\n";
		for (int i = 0; i < s.length; i++) {
			System.out.print(c.getNode()[i] + " ");
			relresult=relresult+c.getNode()[i] + " ";
			for (int j = 0; j < s.length; j++) {
				System.out.print(s[i][j] + " ");
				relresult=relresult+s[i][j] + " ";
			}
			System.out.println();
			relresult=relresult+"\n";
		}

		
		for (int i = 0; i < f.length; i++) {
			System.out.print("  " + c.getNode()[i] + " ");
			corresult=corresult+"  " + c.getNode()[i] + " ";
		}
		System.out.println();
		corresult=corresult+"\n";
		for (int i = 0; i < f.length; i++) {
			System.out.print(c.getNode()[i] + " ");
			corresult=corresult+c.getNode()[i] + " ";
			for (int j = 0; j < f.length; j++) {
				System.out.print(f[i][j] + " ");
				corresult=corresult+f[i][j] + " ";
			}
			System.out.println();
			corresult=corresult+"\n";
		}
		
		corresult=corresult.replaceAll("\n", "<br>");
		corresult=corresult.replaceAll(" ", "&nbsp");
		relresult=relresult.replaceAll("\n", "<br>");
		relresult=relresult.replaceAll(" ", "&nbsp");
		
	     
		return "SUCCESS";
	}
}
