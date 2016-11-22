package com.hainu.cs.circulationchecking.entity;

import java.util.ArrayList;

public class ClassFigure {
	private String name;
	private String objname;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private ArrayList<String> attr;
	private ArrayList<String> oper;
	private int x;
	private int y;
	public ClassFigure(String id,String name,ArrayList<String> attr,ArrayList<String> oper){
		this.id=id;
		this.name=name;
		this.attr=attr;
		this.oper=oper;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name =name;
	}
	public String getObjname(){
		return this.objname;
	}
	public void setObjname(String name){
		this.objname=name;
	}
	public ArrayList<String> getAttr(){
		return this.attr;
	}
	public ArrayList<String> getOper(){
		return this.oper;
	}
	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y=y;
	}
}
