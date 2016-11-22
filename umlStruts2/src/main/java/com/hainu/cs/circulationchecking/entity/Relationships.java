package com.hainu.cs.circulationchecking.entity;

public class Relationships {
	private String id;
	//the type of relationship (e.g. aggregation)
	private String type;
	//the begin class id of the relationship
	private String from;
	//the end class id of the relationship
	private String to;
	
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getFrom(){
		return this.from;
	}
	public void setFrom(String from){
		this.from=from;
	}
	public String getTo(){
		return this.to;
	}
	public void setTo(String to){
		this.to=to;
	}
}
