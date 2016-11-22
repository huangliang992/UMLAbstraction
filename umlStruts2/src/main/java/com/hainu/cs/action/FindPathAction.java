package com.hainu.cs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.FindPathsOfTwoNode;
import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.entity.PathOfG;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FindPathAction extends ActionSupport{
	private String begin;
	private String end;
	private String result="";
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

	public String execute() throws Exception{
		ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        Object filepath=session.get("filepath"); 
        String fp=filepath.toString();
        //System.out.println(fp);
        //System.out.println(begin+"  "+end);
        
        FindPathsOfTwoNode ft=new FindPathsOfTwoNode();
        ft.setBegin(begin);
        ft.setEnd(end);
        ft.findPathsOfG(fp);
        
        ArrayList<PathOfG> t=ft.getPaths();
        for(int k=0;k<t.size();k++){
		PathOfG m=t.get(k);
		ArrayList<String> node=m.getNode();
		ArrayList<String> edge=m.getEdge();
		//System.out.println(node.size());
		result=result+"{path:";
		for(int i=0;i<node.size();i++){
			if(i<node.size()-1){
			result=result+node.get(i)+"--"+edge.get(i)+"--";
			//System.out.print(node.get(i)+" ");
			}else result=result+node.get(i);
		}
		result=result+"}"+"<br>";
        }
        
      
        return "SUCCESS";
	}
}
