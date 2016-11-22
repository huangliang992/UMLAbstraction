package com.hainu.cs.action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.hainu.cs.circulationchecking.controler.ShowAbstractedClass;
import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetBigPictureAction extends ActionSupport{
	private String level;//	接受抽象的层次
	private String number;//接受抽象保留的类的个数
	
	private String classes1;//大图类信息的JSON字符串
	private String ass1;//大图关联的JSON字符串
	private String agg1;//聚集关系的JSON字符串
	private String gl1;//泛化关系的JSON字符串
	private String dp1;//依赖关系的JSON字符串
	private String cp1;//组合关系
	
	
	public String getClasses1() {
		return classes1;
	}
	public void setClasses1(String classes1) {
		this.classes1 = classes1;
	}
	public String getAss1() {
		return ass1;
	}
	public void setAss1(String ass1) {
		this.ass1 = ass1;
	}
	public String getAgg1() {
		return agg1;
	}
	public void setAgg1(String agg1) {
		this.agg1 = agg1;
	}
	public String getGl1() {
		return gl1;
	}
	public void setGl1(String gl1) {
		this.gl1 = gl1;
	}
	public String getDp1() {
		return dp1;
	}
	public void setDp1(String dp1) {
		this.dp1 = dp1;
	}
	public String getCp1() {
		return cp1;
	}
	public void setCp1(String cp1) {
		this.cp1 = cp1;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void abstractInfo(ShowAbstractedClass sh) throws Exception{
		JSONObject cjb=new JSONObject();//类的JSON对象
        JSONObject asjb=new JSONObject();//关联
        JSONObject agjb=new JSONObject();
        JSONObject gljb=new JSONObject();
        JSONObject dpjb=new JSONObject();
        JSONObject cpjb=new JSONObject();
        
        HashMap<String,ClassFigure> cs=sh.getClf();
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
        	classes1=cjb.toString();
        }
        
        HashMap<String,ASFigure> as=sh.getAsf();
        int k1=0;
        for(Map.Entry<String, ASFigure>entry:as.entrySet()){
        	k1++;
        	ASFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	asjb.put("key"+k1,obj);
        	ass1=asjb.toString();
        }
        
        
        HashMap<String,AGFigure> ag=sh.getAgf();
        int k2=0;
        for(Map.Entry<String, AGFigure>entry:ag.entrySet()){
        	k2++;
        	AGFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	agjb.put("key"+k2,obj);
        	agg1=agjb.toString();
        }
        
        
        HashMap<String,GLFigure> gll=sh.getGlf();
        int k3=0;
        for(Map.Entry<String, GLFigure>entry:gll.entrySet()){
        	k3++;
        	GLFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	gljb.put("key"+k3,obj);
        	gl1=gljb.toString();
        }
        
        HashMap<String,DPFigure> dep=sh.getDpf();
        int k4=0;
        for(Map.Entry<String, DPFigure>entry:dep.entrySet()){
        	k4++;
        	DPFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	dpjb.put("key"+k4,obj);
        	dp1=dpjb.toString();
        }
        
        HashMap<String,CPFigure> cop=sh.getCpf();
        int k5=0;
        for(Map.Entry<String, CPFigure>entry:cop.entrySet()){
        	k5++;
        	CPFigure value=entry.getValue();
        	JSONObject obj=new JSONObject();
        	obj.put("sourceid", value.getSourceId());
        	obj.put("destid", value.getDestId());
        	cpjb.put("key"+k5,obj);
        	cp1=cpjb.toString();
        }
       
	}
	
	public String execute() throws Exception{
		ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        Object filepath=session.get("filepath"); 
        String fp=filepath.toString();

        ShowAbstractedClass sh=new ShowAbstractedClass();
         sh.genInfo(fp);
         abstractInfo(sh);
         session.put("classes1", classes1);
         session.put("ass1", ass1);
         session.put("agg1", agg1);
         session.put("gl1", gl1);
         session.put("dp1", dp1);
         session.put("cp1", cp1);
        
        return "SUCCESS";
	}
	
	public String executea() throws Exception{
		ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        Object filepath=session.get("filepath"); 
        String fp=filepath.toString();

        ShowAbstractedClass sh=new ShowAbstractedClass();
        System.out.println(number);
        int x=Integer.parseInt(number);
        System.out.println(x);
         sh.genInfo(fp,x);
         abstractInfo(sh);
         session.put("classes1", classes1);
         session.put("ass1", ass1);
         session.put("agg1", agg1);
         session.put("gl1", gl1);
         session.put("dp1", dp1);
         session.put("cp1", cp1);
        
        return "SUCCESS";
	}
	
	public String executeb() throws Exception{
		ActionContext actionContext = ActionContext.getContext();   
        Map session = actionContext.getSession();   
        Object filepath=session.get("filepath"); 
        String fp=filepath.toString();

        ShowAbstractedClass sh=new ShowAbstractedClass();
        float x=Float.parseFloat(level);
        float y=x/10;
         sh.genInfo(fp,y);
         abstractInfo(sh);
         session.put("classes1", classes1);
         session.put("ass1", ass1);
         session.put("agg1", agg1);
         session.put("gl1", gl1);
         session.put("dp1", dp1);
         session.put("cp1", cp1);
        
        return "SUCCESS";
	}
}
