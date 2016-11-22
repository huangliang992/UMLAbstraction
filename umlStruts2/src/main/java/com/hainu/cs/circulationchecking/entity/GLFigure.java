package com.hainu.cs.circulationchecking.entity;

public class GLFigure {
	private String sourceid;
	private String destid;
	private String sourcename;
	private String destname;
	public String getSourceId(){
		return this.sourceid;
	}
	public String getDestId(){
		return this.destid;
	}
	public void setSourceId(String id){
		this.sourceid=id;
	}
	public void setDestId(String id){
		this.destid=id;
	}
	public GLFigure(String sourceid,String destid){
		this.sourceid=sourceid;
		this.destid=destid;
	}
}
