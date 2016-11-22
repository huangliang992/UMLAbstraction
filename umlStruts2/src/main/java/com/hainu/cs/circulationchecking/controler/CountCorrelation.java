package com.hainu.cs.circulationchecking.controler;

import java.util.ArrayList;

import com.hainu.cs.circulationchecking.entity.PathOfG;

/**
 * 根据节点和节点之间的关系类型，与节点和节点之间的距离
 * 计算节点与节点之间的相关度。相关度越高，说明这两个节点越相关。
 * @author hl
 *
 */
public class CountCorrelation {
	
	private float[][] corre;
	private String [][] reltype;
	
	public void init(String[]node, String[][] edge){
		this.corre=countCorrelation(node,edge);
		this.reltype=countRelationType(node,edge);
	}
	
	public float[][] getCorrelation(){
		return this.corre;
	}
	
	public String[][] getRelationType(){
		return this.reltype;
	}
	
	/**
	 * 要找到任意连个节点之间的所有路径，然后对每一条路径，依据关系计算在这条路径上
	 * 两端节点的相关度。如果有多条路径取最大的相关度值
	 * @param node
	 * @param edge
	 * @return
	 */
	public float[][] countCorrelation(String[] node, String [][] edge){
		float[][] correlation = new float[node.length][node.length];
		String []type={"GL","DP","AG","AS","GLr","DPr","AGr"};
		float [] weight={1,(float) 0.8,(float) 0.7,(float) 0.5,1,(float) 0.8,(float) 0.7};
		for(int i=0;i<node.length;i++){
			for(int j=0;j<node.length;j++){
				float cor=0;
				FindPathsOfTwoNode f=new FindPathsOfTwoNode();
				f.findPaths(node, edge, node[i], node[j]);
				//找到两个节点之间的所有路径
				ArrayList<PathOfG> p=f.getPaths();
				//对每一条路径上两段节点的相关度计算
				for(int k=0;k<p.size();k++){
					PathOfG pg=p.get(k);
					float temp=1;
					for(int m=0;m<pg.getEdge().size();m++){
						for(int n=0;n<type.length;n++){
							if(pg.getEdge().get(m).equals(type[n])){
								//System.out.println("yes");
								temp=temp*weight[n];
								System.out.print(temp+" ");
							}
						}
						//获取最大的相关度	
					}
					if(temp>cor){
							cor=temp;
					}
					System.out.println(cor);
				}
				correlation[i][j]=cor;
			}
		}
		return correlation;
	}
	
	public String[][] countRelationType(String[] node, String [][] edge){
		String []type={"GL","DP","AG","AS","GLr","DPr","AGr"};
		float [] weight={1,(float) 0.8,(float) 0.7,(float) 0.5,1,(float) 0.8,(float) 0.7};
		String[][] retype=new String[node.length][node.length];
		for(int i=0;i<node.length;i++){
			for(int j=0;j<node.length;j++){
				//先找i，j之间所有的路径
				FindPathsOfTwoNode fp=new FindPathsOfTwoNode();
				fp.findPaths(node, edge, node[i], node[j]);
				ArrayList<PathOfG> ps=fp.getPaths();
				System.out.println(ps.size());
				//i,j之间最终的关系判断（到底选哪一条路径的结果-依据可信度，可信度相同的话，选关系强度大的）
				String finalretype="None";
				float finReliability=0;
				for(int k=0;k<ps.size();k++){
					AbstractPaths abs=new AbstractPaths();
					abs.absPath(ps.get(k));
					float rer=abs.getReliability();
					String rel="None";
					//最终有抽象的结果
					if(abs.getEdgeResult().size()==1){
						rel=abs.getEdgeResult().get(0);
						//System.out.println("yes");
					}
					//选取可信度最大的结果
					if(rer>finReliability){
						finReliability=rer;
						finalretype=rel;
					}
					//如果可信度相同，选取关系强度最大的
					if(rer==finReliability){
						for(int m=0;m<type.length;m++){
							if(rel.equals(type[m])){
								for(int n=0;n<type.length;n++){
									if(finalretype.equals(type[n])){
										if(weight[m]>weight[n]){
											finalretype=rel;
										}
									}
								}
							}
						}
					}
				}
				retype[i][j]=finalretype;
			}
		}
		return retype;
	}
	
	
	
	public static void main(String[] args){
		String filepath="C:/Users/hl/Desktop/example.uml";
		CreateGraph c=new CreateGraph();
		c.initGraph(filepath);
		CountCorrelation co=new CountCorrelation();
		float[][] f=co.countCorrelation(c.getNode(), c.getEdge());
		
		for(int i=0;i<f.length;i++){
			System.out.print("  "+c.getNode()[i]+" ");
		}
		System.out.println();
		for(int i=0;i<f.length;i++){
			System.out.print(c.getNode()[i]+" ");
			for(int j=0;j<f.length;j++){
				System.out.print(f[i][j]+" ");
			}
			System.out.println();
		}
		
		
		String [][] s=co.countRelationType(c.getNode(), c.getEdge());
		for(int i=0;i<s.length;i++){
			System.out.print("  "+c.getNode()[i]+" ");
		}
		System.out.println();
		for(int i=0;i<s.length;i++){
			System.out.print(c.getNode()[i]+" ");
			for(int j=0;j<s.length;j++){
				System.out.print(s[i][j]+" ");
			}
			System.out.println();
		}
	}
}
