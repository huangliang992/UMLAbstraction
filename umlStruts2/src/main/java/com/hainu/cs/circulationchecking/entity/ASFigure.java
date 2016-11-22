package com.hainu.cs.circulationchecking.entity;

public class ASFigure {
	private String sourceid;
	private String destid;
	private String sourcename;
	private String destname;
	public String getSourceId(){
		return this.sourceid;
	}
	public void setSourceId(String id){
		this.sourceid=id;
	}
	public String getSourceName(){
		return this.sourcename;
	}
	public void setSourceName(String name){
		this.sourcename=name;
	}
	public String getDestId(){
		return this.destid;
	}
	public String getDestName(){
		return this.destname;
	}
	public void setDestId(String id){
		this.destid=id;
	}
	public void setDestName(String name){
		this.destname=name;
	}
	
	public ASFigure(String sourceid,String destid){
		this.sourceid=sourceid;
		this.destid=destid;
	}
}
