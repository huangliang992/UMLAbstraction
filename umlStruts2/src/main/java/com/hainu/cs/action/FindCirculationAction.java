package com.hainu.cs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.FindCirculationsContainsN;
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

public class FindCirculationAction extends ActionSupport{
	private String ckclass;
	private String cirresult="";
	
	
	public String getCirresult() {
		return cirresult;
	}
	public void setCirresult(String cirresult) {
		this.cirresult = cirresult;
	}
	public String getCkclass() {
		return ckclass;
	}
	public void setCkclass(String ckclass) {
		this.ckclass = ckclass;
	}
	
	
	public String execute() throws Exception{
		ActionContext actioncontext=ActionContext.getContext();
		Map session=actioncontext.getSession();
		Object filepath=session.get("filepath");
		String fp=filepath.toString();
		System.out.println(fp);
        System.out.println(ckclass);
        FindCirculationsContainsN f=new FindCirculationsContainsN();
        f.setNode(ckclass);
        f.findCir(fp);
        ArrayList<PathOfG> t=f.getCirculation();
		System.out.println(t.size());
		for(int i=0;i<t.size();i++){
			PathOfG p=t.get(i);
			ArrayList<String> node=p.getNode();
			ArrayList<String> edge=p.getEdge();
			System.out.print("Circulation:");
			cirresult=cirresult+"{Circulation:";
			for(int j=0;j<node.size();j++){
				if(j<node.size()-1){
				System.out.print(node.get(j)+"--"+edge.get(j)+"--");
				cirresult=cirresult+node.get(j)+"--"+edge.get(j)+"--";
				}else {
					System.out.print(node.get(j));
					cirresult=cirresult+node.get(j);
				}
			}
			System.out.println();
			cirresult=cirresult+"}<br>";
		}
	      
		return "SUCCESS";
	}
	}

