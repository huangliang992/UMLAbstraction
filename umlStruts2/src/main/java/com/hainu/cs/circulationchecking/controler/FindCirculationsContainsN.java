package com.hainu.cs.circulationchecking.controler;

import java.util.ArrayList;

import com.hainu.cs.circulationchecking.entity.PathOfG;

public class FindCirculationsContainsN {
/**
 * 1.输入图
 * 2.对图中的任意一个节点N，判断出包含它的环
 * 3.首先找到与该节点相邻节点的节点的集合S
 * 4.任意选取S中的两个顶点，通过FindPathOfTwoNode找到这俩个顶点的所有路径，排除掉包含N的路径，
 * 其他的路径加上N即构成循环。假设这样的路径有a(i)条。i表示选取俩个顶点的第i中选法。
 * 5.包含N的不重复的总的环的个数就是a(1)+a(2)+.....
 */
	private String node;
	private ArrayList<PathOfG> cir=new ArrayList<PathOfG>();
	
	public void setNode(String node){
		this.node=node;
	}
	
	public ArrayList<PathOfG> getCirculation(){
		return this.cir;
	}
	
	public void findCir(String filepath){
		CreateGraph g=new CreateGraph();
		g.initGraph(filepath);
		String[] nd=g.getNode();
		String[][] eg=g.getEdge();
		//System.out.println(nd[1]);
		
		for(int i=0;i<nd.length;i++){
			if(nd[i].equals(node)){
				for(int j=0;j<nd.length;j++){
					if(eg[i][j].equals("NONE")!=true){//获取node的第一个相邻顶点
						//System.out.println(eg[i][j]);
						for(int k=j+1;k<nd.length;k++){
							if(eg[i][k].equals("NONE")!=true){//获取第二个相邻顶点
								FindPathsOfTwoNode fpot=new FindPathsOfTwoNode();
								
								fpot.setBegin(nd[j]);
								fpot.setEnd(nd[k]);
								fpot.findPathsOfG(filepath);
								ArrayList<PathOfG> ap=fpot.getPaths();
								//获取不含node的路径
								for(int n=0;n<ap.size();n++){
									PathOfG p=ap.get(n);
									boolean flag=true;
									ArrayList<String> no=p.getNode();
									ArrayList<String> rl=p.getEdge();
									for(int m=0;m<no.size();m++){
										if(no.get(m).equals(node)){
											flag=false;
											break;
										}
									}
									if(flag){
										PathOfG temp=new PathOfG();
										ArrayList<String> tpNode=temp.getNode();
										ArrayList<String> tpEdge=temp.getEdge();
										//构造环的顶点集合从node开始到node结束
										tpNode.add(node);
										for(int m=0;m<no.size();m++){
											tpNode.add(no.get(m));
										}
										tpNode.add(node);
										//构造环的边的集合
										tpEdge.add(eg[i][j]);
										for(int m=0;m<rl.size();m++){
											tpEdge.add(rl.get(m));
										}
										tpEdge.add(eg[i][k]);
										cir.add(temp);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		FindCirculationsContainsN e=new FindCirculationsContainsN();
		e.setNode("B");
		e.findCir("C:/Users/hl/Desktop/test1.uml");
		ArrayList<PathOfG> t=e.getCirculation();
		System.out.println(t.size());
		for(int i=0;i<t.size();i++){
			PathOfG p=t.get(i);
			ArrayList<String> node=p.getNode();
			ArrayList<String> edge=p.getEdge();
			System.out.print("Circulation:");
			for(int j=0;j<node.size();j++){
				if(j<node.size()-1){
				System.out.print(node.get(j)+"--"+edge.get(j)+"--");
				}else System.out.print(node.get(j));
			}
			System.out.println();
		}
	}
}
