package com.hainu.cs.circulationchecking.controler;

import java.util.ArrayList;

import com.hainu.cs.circulationchecking.entity.PathOfG;

public class AbstractPaths {
	private ArrayList<String> absResults=new ArrayList<String>();
	private ArrayList<String> nodeResult=new ArrayList<String>();
	private ArrayList<String> edgeResult=new ArrayList<String>();
	private float reliability=0;
	
	public ArrayList<String> getNodeResult(){
		return this.nodeResult;
	}
	public ArrayList<String> getEdgeResult(){
		return this.edgeResult;
	}
	public ArrayList<String> getAbstractResults(){
		return this.absResults;
	}
	
	public float getReliability(){
		return this.reliability ;
	}
	
	public void init(String filepath, String begin, String end){
		FindPathsOfTwoNode fp=new FindPathsOfTwoNode();
		fp.setBegin(begin);//输入俩个类
		fp.setEnd(end);
		fp.findPathsOfG(filepath);//初始化
		ArrayList<PathOfG> ps=fp.getPaths();//获取这两个类之间的所有路径
		for(int i=0;i<ps.size();i++){
			PathOfG p=ps.get(i);//获取所有路径中的一条
			String temp=absPath(p);//获取一条路径的抽象结果
			for(int j=0;j<nodeResult.size();j++){
				if(j<nodeResult.size()-1){
			System.out.print("结果"+nodeResult.get(j)+"--"+edgeResult.get(j)+"--");
				}else System.out.println(nodeResult.get(j)+"  "+reliability);
			}
			absResults.add(temp);//将该路径的抽象结果保存
		}
		
	}
	//优先执行可信度高的规则
	public String absPath(PathOfG p){
		
		String str="";//str获取抽象前的一条原始路径
		String stre="";//用来接收抽象过程中的信息
		ArrayList<String> node=p.getNode();//最后的抽象的两端的类
		ArrayList<String> edge=p.getEdge();//放最后的抽象关系，如果只有一个的话。

		float finreliability=1;//抽象完后的最终可信度
		for(int i=0;i<node.size();i++){
			if(i<node.size()-1){
				str=str+node.get(i)+"--"+edge.get(i)+"--";
			}else str=str+node.get(i)+"\n";
		}
		boolean flag=true;//最初假设该路径时可以抽象的
		while(flag){//一次while完成一次规则抽象，最后完成该路径所有规则的抽象
			flag=false;
			int max=0;//初始化最大的可信度规则
			int k1=-1;//初始化第一个关系的下标
			int k2=-1;//初始化第二个关系的下标
			
			for(int i=0;i<edge.size()-1;i++){//找出可信度最大的规则
				int relia=getRelialility(edge.get(i),edge.get(i+1));
				if(relia>max){
					max=relia;//获取最大的可信度规则
					flag=true;
					k1=i;//得到可信度最大的规则的第一个关系下标
					k2=i+1;
				}
			}
			
			if(k1!=-1&&k2!=-1){
			String str1=getRelation(edge.get(k1),edge.get(k2));
			finreliability=finreliability*max/100;
			//应用的一条可信度最大的规则
			String str3="Apply "+edge.get(k1)+" x "+node.get(k2)+" x "+edge.get(k2)+" equals "+str1+" "+max+" get: ";
			//修改列表，将中间的类从列表中移除，修改关系，将多余关系移除，完成一次抽象
			edge.set(k1, str1);
			edge.remove(k2);
			node.remove(k2);
			//应用完一条规则后抽象后的路径
			String str2="";
			for(int j=0;j<node.size();j++){
				if(j<node.size()-1){
					str2=str2+node.get(j)+"--"+edge.get(j)+"--";
				}else str2=str2+node.get(j);
			}
			stre=stre+str3+str2+"\n";
			}
		}
		
		nodeResult=node;//抽象后的节点结果
		edgeResult=edge;//抽象后的关系的结果
		reliability=finreliability;
		System.out.println("For path: "+str+stre+"Final reliability : "+finreliability+"\n");
		return "For path: "+str+stre+"Final reliability : "+finreliability+"\n";
	}
	
	//查表获取两个关系抽象后的关系
	public String getRelation(String re1, String re2){

		String[] re={"AS","AG","DP","GL","AGr","DPr","GLr"};
		String[][] rule={
				{"AS","AS","DP","AS","AS","DPr","AS"},
				{"AS","AG","DP","AG","X","DPr","AG"},
				{"AS","DP","DP","DP","DP","X","DP"},
				{"AS","AG","DP","GL","AGr","DPr","X"},
				{"AS","X","DP","AGr","AGr","DPr","AGr"},
				{"DPr","DPr","X","DPr","DPr","DPr","DPr"},
				{"AS","AG","DP","X","AGr","DPr","GLr"}
		};
			int k1=-1;
			int k2=-1;
		for(int i=0;i<re.length;i++){
			
			if(re1.equals(re[i])){
				k1=i;
			}
			if(re2.equals(re[i])){
				k2=i;
			}
		}
		return rule[k1][k2];
	}
	
	//查表获取两个关系抽象后的可信度
	public int getRelialility(String re1, String re2){

		String[] re={"AS","AG","DP","GL","AGr","DPr","GLr"};
		int[][] reliability={
				{100,100,50,70,70,50,100},
				{90,100,50,50,0,80,100},
				{50,70,100,50,80,0,100},
				{100,100,100,100,100,100,0},
				{100,0,100,80,100,70,100},
				{50,100,0,50,50,100,100},
				{70,80,50,0,50,50,100}
		};
			int k1=-1;
			int k2=-1;
		for(int i=0;i<re.length;i++){
			
			if(re1.equals(re[i])){
				k1=i;
			}
			if(re2.equals(re[i])){
				k2=i;
			}
		}
		if(k1!=-1&&k2!=-1){
		return reliability[k1][k2];
		}else return 0;
	}
	
	public static void main(String[] args){
		AbstractPaths e=new AbstractPaths();
		System.out.println("shuchu");
		e.init("C:/Users/hl/Desktop/test1.uml", "D", "E");
	}
}
