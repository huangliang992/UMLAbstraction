package com.hainu.cs.circulationchecking.controler;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author hl
 *对 classrank进行排序，然后分层，想法是分成3到个5层，每次层次代表一中层次的抽象。
 */
public class SortClass {
	private ClassRank c;
	private String[] node;
	private float[] rank;
	
	public void init(String filepath){
		c=new ClassRank();
		c.init(filepath);
		String [] cla=c.getNode();
		float [] rk=c.getClassRank();
		sortRank(rk,cla);
		node=cla;
		rank=rk;
	}
	
	public float[] getRankSorted(){
		return this.rank;
	}
	public String[] getNodeSorted(){
		return this.node;
	}
	
	/**
	 * 
	 * @param rank
	 * 对rank进行插入排序
	 * @return
	 */
	public void sortRank(float [] rank,String [] node){
		//每次选一个最大的放在开头
		for(int i=0;i<rank.length;i++){
			for(int j=i+1;j<rank.length;j++){
				if(rank[j]>rank[i]){
					float temp=rank[i];
					rank[i]=rank[j];
					rank[j]=temp;
					String temp1=node[i];
					node[i]=node[j];
					node[j]=temp1;
				}
			}
		}
	}
	public static void main(String[] args){
		String filepath="C:/Users/hl/Desktop/test1.uml";
		SortClass s=new SortClass();
		s.init(filepath);
		for(int i=0;i<s.getRankSorted().length;i++){
			System.out.println(s.getNodeSorted()[i]+": "+s.getRankSorted()[i]+" " );
		}
	}
}
