package com.hainu.cs.circulationchecking.controler;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ClassRank {
	//最终的class 的rank数组
	private float [] rank;
	private String [] node;
	
	public String [] getNode(){
		return this.node;
	}
	
	//记录概率转移矩阵
	private String prectangle="     ";
	
	//概率转移矩阵
	private float[][] transmatrix;
	
	
	public float[][] getTransmatrix() {
		return transmatrix;
	}

	public void setTransmatrix(float[][] transmatrix) {
		this.transmatrix = transmatrix;
	}

	public String getPrectangle(){
		return this.prectangle;
	}
	
	
	//记录迭代过程
	private String iterateprocess="";
	public String getIterateprocess(){
		return this.iterateprocess;
	}
	
	public float [] getClassRank(){
		return this.rank;
	}
	
	/*
	 * 对类图中的rank类计算
	 */
	public void init(String filepath){
		CreateGraph g=new CreateGraph();
		g.initGraph(filepath);
		node=g.getNode();
		String [][] edge=g.getEdge();
		rank=rankClass(node,edge);
	}
	
	/*
	 * 对图中的节点的rank值进行计算
	 */
	public float [] rankClass(String [] node,String[][] edge){
		
		for(int k=0;k<node.length;k++){
			prectangle=prectangle+node[k]+"   ";
		}
		prectangle=prectangle+"\n";
		//GL DP AG AS GLr DPr AGr
		String []type={"GL","DP","AG","AS","GLr","DPr","AGr"};
		float [] weight={1,(float) 0.8,(float) 0.7,(float) 0.6,1,(float) 0.8,(float) 0.7};
		int length=node.length;
		//节点的概率转移矩阵
		float[][] change=new float [length][length];
		
		transmatrix=change;
		
		//概率转移矩阵的转置矩阵
		float[][] changer=new float [length][length];
		
		//最终的节点的rank数组
		float [] crank= new float [length];
		//每次迭代后的临时矩阵
		float [] temp= new float[length];
		//初始rank值设置1/length
		float [] temp1=new float[length];
		
		
		for(int i=0;i<length;i++){
			
			double t=1000/length;
			//控制小数点位数
			BigDecimal b=new BigDecimal(t);
			temp[i]=(float) b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
			//System.out.println(temp[i]);
		}
		
		//依据边的类型构造概率转移矩阵
		for(int i=0;i<length;i++){
			
			prectangle=prectangle+node[i]+"  ";
			//计算总的权值
			float total=0;
			for(int j=0;j<length;j++){
				String reltype=edge[i][j];
				//判断概率转移的权值
				for(int k=0;k<7;k++){
					if(reltype.equals(type[k])){
						total=total+weight[k];
					}
				}
			}
			for(int j=0;j<length;j++){
				String reltype=edge[i][j];
				//判断概率转移的权值
				float p=0;
				for(int k=0;k<7;k++){
					if(reltype.equals(type[k])){
						
						p=(float)weight[k]/total;
						BigDecimal b=new BigDecimal(p);
						p=(float) b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
						//设置概率转移矩阵的值
						//change[i][j]=p;
						break;
					}
				}
				change[i][j]=p;
				prectangle=prectangle+p+"  ";
				//System.out.print(p+" ");
			}
			//System.out.println();
			prectangle=prectangle+"\n";
		}
		
		System.out.println(prectangle);
		System.out.println("----------------------------------分割线---------------------------------");
		
		//计算概率转移矩阵
		for(int i=0;i<length;i++){
			for(int j=0;j<length;j++){
				changer[j][i]=change[i][j];
			}
		}
		
		
		//概率转置矩阵乘初始rank矩阵,行列相乘,一次迭代
		int k=0;
		while(judge(temp1,temp)&&k<50){//最终rank收敛后跳出循环
		//前一次rank，为了和迭代后的rank做比较用
			k++;
		for(int i=0;i<length;i++){
			temp1[i]=temp[i];
		}
		for(int i=0;i<length;i++){
			float count=0;
			for(int j=0;j<length;j++){
				//计算迭代后class的rank
				count=count+changer[i][j]*temp[j];
				//System.out.println(count);
			}
			crank[i]=count;
			
			iterateprocess=iterateprocess+"Class"+i+" "+node[i]+"  "+count+"   ";
			//System.out.print(node[i]+":class"+i+": "+count+" ");
		}
		
		//标准化
		float total=0;
		for(int i=0;i<length;i++){
			total=total+crank[i];
		}
		for(int i=0;i<length;i++){
			crank[i]=crank[i]/total*1000;
		}
		//一次迭代后同步crank和temp
				for(int i=0;i<length;i++){
					temp[i]=crank[i];
				}
		iterateprocess=iterateprocess+"\n";
		//System.out.println();
		}
		System.out.println(iterateprocess);
		return crank;
	}
	
	public boolean judge(float[] crank, float[] temp){
		boolean flag=true;
		for(int i=0;i<temp.length;i++){
			if((crank[i]-temp[i])/crank[i]<0.01&&(temp[i]-crank[i])/crank[i]<0.01){
				flag=false;
			}else {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public static void main(String [] args){
		String filepath="C:/Users/hl/Desktop/example.uml";
		ClassRank e=new ClassRank();
		e.init(filepath);
		System.out.println(e.getPrectangle());
		System.out.println(e.getIterateprocess());
	}
}
