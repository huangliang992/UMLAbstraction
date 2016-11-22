package com.hainu.cs.circulationchecking.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hainu.cs.circulationchecking.entity.Classes;
import com.hainu.cs.circulationchecking.entity.Relationships;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManager;

public class CreateGraph {
	/**
	 * 1.将UML类图重新构造出一般的图的形式，也就是顶点和边的集合
	 * 2.用领接矩阵来表示这个图
	 */
	private static String[] node=null;
	private static String[][] edge=null;
	//create the graph
	public void initGraph(String filepath){
		XMLManager m=new XMLManager();
		m.parse(filepath);
		HashMap<String,Classes> cs=m.getClasses();
		HashMap<String,Relationships> rs=m.getRelationships();
		int l=cs.size();
		node=new String[l];
		edge=new String[l][l];
		int i=0;
		//构造顶点的集合
		for(Map.Entry<String, Classes>entry:cs.entrySet()){
			Classes value=entry.getValue();
			node[i]=value.getName();
			i++;
		}
		//构造领接矩阵
		for (int j = 0; j < l; j++) {
			for (int k = 0; k < l; k++) {
				String jid="none";
				String kid="none";
				edge[j][k]="NONE";
				for(Map.Entry<String, Classes>entry:cs.entrySet()){
					Classes value=entry.getValue();
					if(value.getName().equals(node[j])){
						jid=value.getId();
					}
					if(value.getName().equals(node[k])){
						kid=value.getId();
					}
				}
				for (Map.Entry<String, Relationships> entry : rs.entrySet()) {
					String key = entry.getKey();
					Relationships value = entry.getValue();
					if(value.getFrom().equals(jid)&&value.getTo().equals(kid)){
						edge[j][k]=value.getType();
					}
					if(value.getFrom().equals(kid)&&value.getTo().equals(jid)){
						if(value.getType().equals("AS")){
							edge[j][k]="AS";
						}
						else edge[j][k]=value.getType()+"r";
					}
				}
			}
		}
	}
	
	public String[] getNode(){
		return this.node;
	}
	public String[][] getEdge(){
		return this.edge;
	}
	
	public static void main(String[] args){
		CreateGraph f=new CreateGraph();
		f.initGraph("C:/Users/hl/Desktop/2/test1.uml");
		if(node!=null){
			for(int i=0;i<node.length;i++){
				System.out.println(node[i]);
			}
		}
		if(edge!=null){
			for(int i=0;i<edge.length;i++){
				System.out.println();
				for(int j=0;j<edge.length;j++){
					System.out.print(edge[i][j]+" ");
				}
			}
		}
	}
}
