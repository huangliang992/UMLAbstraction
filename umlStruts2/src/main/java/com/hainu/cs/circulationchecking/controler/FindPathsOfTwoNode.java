package com.hainu.cs.circulationchecking.controler;

import java.util.ArrayList;

import com.hainu.cs.circulationchecking.entity.PathOfG;
	/**
	 * 1.目的：输入图中任意俩个不同的类，获取这俩个类之间的所有的路劲。
	 * 2.通过回溯法来获得俩个类之间的所有的路径
	 */
public class FindPathsOfTwoNode {
	
	private String begin;
	private String end;
	private ArrayList<PathOfG> paths=new ArrayList<PathOfG>();

	
	public void setBegin(String bg){
		this.begin=bg;
	}
	public void setEnd(String ed){
		this.end=ed;
	}
	
	public ArrayList<PathOfG> getPaths(){
		return this.paths;
	}
	
	public void findPathsOfG(String filepath){
		CreateGraph g=new CreateGraph();
		g.initGraph(filepath);
		String[] node=g.getNode();
		String[][] edge=g.getEdge();
		findPaths(node,edge,begin,end);
	}
	
	
	PathOfG p=new PathOfG();
	ArrayList<String> nd=p.getNode();
	ArrayList<String> eg=p.getEdge();	
	public void findPaths(String[] node, String[][] edge, String begin, String end){
		for(int i=0;i<node.length;i++){
			if(node[i].equals(begin)){
				//System.out.println(node[i]);
				nd.add(node[i]);
				for(int j=0;j<node.length;j++){
					//System.out.println(node[j]+" "+judgeNode(node[j],nd));
					if(((edge[i][j].equals("NONE"))!=true)&&judgeNode(node[j],nd)){
						eg.add(edge[i][j]);
						if(node[j].equals(end)){
							//put the path into paths array
							nd.add(node[j]);
							System.out.print("Path: ");
							for(int m=0;m<nd.size();m++){
								if(m<nd.size()-1){
								System.out.print(nd.get(m)+"--"+ eg.get(m)+"--");
								}
								else System.out.print(nd.get(m)+" ");
							}
							
							System.out.println();
							//save the paths
							PathOfG temp=new PathOfG();
							ArrayList<String> tnd=temp.getNode();
							ArrayList<String> teg=temp.getEdge();
							for(int m=0;m<nd.size();m++){
								tnd.add(nd.get(m));
							}
							for(int n=0;n<eg.size();n++){
								teg.add(eg.get(n));
							}
							paths.add(temp);
							
							
							nd.remove(nd.size()-1);
							eg.remove(eg.size()-1);
							
							//break;
						}
						else findPaths(node,edge,node[j],end);
					}
				}
			}
		}
		//回溯
		if(nd.isEmpty()!=true){
		nd.remove(nd.size()-1);
		}
		if(eg.isEmpty()!=true){
		eg.remove(eg.size()-1);
		}
	}
	
	
	public boolean judgeNode(String node, ArrayList<String> na){
		boolean flag=true;
		for(int i=0;i<na.size();i++){
			if(node.equals(na.get(i))){
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	public static void main(String[] args){
		FindPathsOfTwoNode e=new FindPathsOfTwoNode();
		e.setBegin("E");
		e.setEnd("End");
		e.findPathsOfG("C:/Users/hl/Desktop/2/test1.uml");
		ArrayList<PathOfG> t=e.getPaths();
		PathOfG m=t.get(0);
		ArrayList<String> node=m.getNode();
		System.out.println(node.size());
		for(int i=0;i<node.size();i++){
			System.out.print(node.get(i)+" ");
		}
	}
	
	
}
