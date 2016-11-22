package com.hainu.cs.circulationchecking.controler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
import com.hainu.cs.circulationchecking.permanant_storage.XMLManagerFul;


public class ShowAbstractedClass{
	private SortClass sc=new SortClass();
	private CountCorrelation cc=new CountCorrelation();
	private CreateGraph cg=new CreateGraph();
	private XMLManagerFul xf=new XMLManagerFul();
	private HashMap<String,ASFigure> asf=new HashMap<String,ASFigure>();
	private HashMap<String,AGFigure> agf=new HashMap<String,AGFigure>();
	private HashMap<String,CPFigure> cpf=new HashMap<String,CPFigure>();
	private HashMap<String,DPFigure> dpf=new HashMap<String,DPFigure>();
	private HashMap<String,GLFigure> glf=new HashMap<String,GLFigure>();
	private HashMap<String,ClassFigure> clf=new HashMap<String,ClassFigure>();
	
	
	
	public HashMap<String, ASFigure> getAsf() {
		return asf;
	}

	public void setAsf(HashMap<String, ASFigure> asf) {
		this.asf = asf;
	}

	public HashMap<String, AGFigure> getAgf() {
		return agf;
	}

	public void setAgf(HashMap<String, AGFigure> agf) {
		this.agf = agf;
	}

	public HashMap<String, CPFigure> getCpf() {
		return cpf;
	}

	public void setCpf(HashMap<String, CPFigure> cpf) {
		this.cpf = cpf;
	}

	public HashMap<String, DPFigure> getDpf() {
		return dpf;
	}

	public void setDpf(HashMap<String, DPFigure> dpf) {
		this.dpf = dpf;
	}

	public HashMap<String, GLFigure> getGlf() {
		return glf;
	}

	public void setGlf(HashMap<String, GLFigure> glf) {
		this.glf = glf;
	}

	public HashMap<String, ClassFigure> getClf() {
		return clf;
	}

	public void setClf(HashMap<String, ClassFigure> clf) {
		this.clf = clf;
	}

	/**
	 * 默认显示rank前1/3的类
	 * @param filepath
	 * @throws Exception
	 */
	public void genInfo(String filepath) throws Exception{
		sc.init(filepath);
		String node[]=sc.getNodeSorted();//获取按rank从大到小的排序
		xf.init(filepath);
		cg.initGraph(filepath);
		String[] ghnode=cg.getNode();//生成的图的节点
		String [][] ghedge=cg.getEdge();//生成的图的边
		cc.init(ghnode, ghedge);
		float[][] correlation=cc.getCorrelation();//获取类之间的相关度矩阵
		String[][] type=cc.getRelationType();//获取类之间的关系矩阵
		HashMap<String,ClassFigure> clf1=xf.getCls();
		int ab1=node.length/3;//认为rank在前1/3的类是重要的
		//将重要的类加入要展示的类HashMap集合中
		for(int i=0;i<ab1;i++){
			for(Map.Entry<String, ClassFigure>entry:clf1.entrySet()){
				String key=entry.getKey();
				ClassFigure value=entry.getValue();
				if(node[i].equals(value.getName())){
					System.out.println(node[i]);
					clf.put(key, value);
				}
			}
		}
		//遍历相关度矩阵，如果相关度大于0.4则认为这两个类之间高度相关，要显示关系
		for(int i=0;i<ab1;i++){
			for(int j=0;j<ab1;j++){
				float corr=getCorOfTwo(node[i],node[j],correlation,ghnode);
				String relt=getRelOfTwo(node[i],node[j],type,ghnode);
				if(corr>0.4&relt!="None"){
					genRelation(node[i],node[j],relt);
				}
			}
		}
	}
	
	/**
	 * 查看前n个重要的类
	 * @param filepath
	 * @param n
	 * @throws Exception
	 */
	public void genInfo(String filepath, int n) throws Exception{
		sc.init(filepath);
		String node[]=sc.getNodeSorted();//获取按rank从大到小的排序
		xf.init(filepath);
		cg.initGraph(filepath);
		String[] ghnode=cg.getNode();//生成的图的节点
		String [][] ghedge=cg.getEdge();//生成的图的边
		cc.init(ghnode, ghedge);
		float[][] correlation=cc.getCorrelation();//获取类之间的相关度矩阵
		String[][] type=cc.getRelationType();//获取类之间的关系矩阵
		HashMap<String,ClassFigure> clf1=xf.getCls();
		int ab1=n;//查看前n个重要的类
		if(n>node.length){
			ab1=node.length;
		}
		//将重要的类加入要展示的类HashMap集合中
		for(int i=0;i<ab1;i++){
			for(Map.Entry<String, ClassFigure>entry:clf1.entrySet()){
				String key=entry.getKey();
				ClassFigure value=entry.getValue();
				if(node[i].equals(value.getName())){
					System.out.println(node[i]);
					clf.put(key, value);
				}
			}
		}
		//遍历相关度矩阵，如果相关度大于0.4则认为这两个类之间高度相关，要显示关系
		for(int i=0;i<ab1;i++){
			for(int j=0;j<ab1;j++){
				float corr=getCorOfTwo(node[i],node[j],correlation,ghnode);
				String relt=getRelOfTwo(node[i],node[j],type,ghnode);
				if(corr>0.4&relt!="None"){
					genRelation(node[i],node[j],relt);
				}
			}
		}
	}
	
	/**
	 * 查看前几分之几的重要的类
	 * @param filepath
	 * @param n 指定前几分之几如0.6
	 * @throws Exception
	 */
	public void genInfo(String filepath, float n) throws Exception{
		sc.init(filepath);
		String node[]=sc.getNodeSorted();//获取按rank从大到小的排序
		xf.init(filepath);
		cg.initGraph(filepath);
		String[] ghnode=cg.getNode();//生成的图的节点
		String [][] ghedge=cg.getEdge();//生成的图的边
		cc.init(ghnode, ghedge);
		float[][] correlation=cc.getCorrelation();//获取类之间的相关度矩阵
		String[][] type=cc.getRelationType();//获取类之间的关系矩阵
		HashMap<String,ClassFigure> clf1=xf.getCls();
		int ab1=(int) (n*node.length);//查看前n个重要的类
		if(n>1){
			ab1=node.length;
			}
		//将重要的类加入要展示的类HashMap集合中
		for(int i=0;i<ab1;i++){
			for(Map.Entry<String, ClassFigure>entry:clf1.entrySet()){
				String key=entry.getKey();
				ClassFigure value=entry.getValue();
				if(node[i].equals(value.getName())){
					System.out.println(node[i]);
					clf.put(key, value);
				}
			}
		}
		//遍历相关度矩阵，如果相关度大于0.4则认为这两个类之间高度相关，要显示关系
		for(int i=0;i<ab1;i++){
			for(int j=0;j<ab1;j++){
				float corr=getCorOfTwo(node[i],node[j],correlation,ghnode);
				String relt=getRelOfTwo(node[i],node[j],type,ghnode);
				if(corr>0.4&relt!="None"){
					genRelation(node[i],node[j],relt);
				}
			}
		}
	}
	
	
	/**
	 * 查找任意两个重要的类之间的相关度
	 * @param row  要查row和colum之间相关度
	 * @param colum
	 * @param correlation 相关度表
	 * @param node 节点
	 * @return
	 */
	public float getCorOfTwo(String row,String colum,float[][] correlation,String[] node){
		int k1=-1;
		int k2=-1;
		for(int i=0;i<node.length;i++){
			if(row.equals(node[i])){
				k1=i;
				}
			if(colum.equals(node[i])){
				k2=i;
			}
			}	
		return correlation[k1][k2];
		}
	/**
	 * 查找任意两个重要的类之间的关系
	 * @param row
	 * @param colum
	 * @param reltp
	 * @param node
	 * @return
	 */
	public String getRelOfTwo(String row,String colum,String[][] reltp,String[] node){
		int k1=-1;
		int k2=-1;
		for(int i=0;i<node.length;i++){
			if(row.equals(node[i])){
				k1=i;
				}
			if(colum.equals(node[i])){
				k2=i;
			}
			}	
		return reltp[k1][k2];
	}
	
	/**
	 * 根据优化后的重要类之间的关系生成，相应的关系的对象
	 * @param row
	 * @param colum
	 * @param rel
	 */
	public void genRelation(String row,String colum,String rel){
		//依据类名查找类的id
		String rid="";
		String cid="";
		for(Map.Entry<String, ClassFigure>entry:clf.entrySet()){
			String key=entry.getKey();
			ClassFigure value=entry.getValue();
			String nm=value.getName();
			if(nm.equals(row)){
				rid=key;
			}
			if(nm.equals(colum)){
				cid=key;
			}
		}
		if(rel.equals("AS")){
			ASFigure afg=new ASFigure(rid,cid);
			asf.put(getRandomString(10), afg);
		}
		if(rel.equals("AG")){
			AGFigure agg=new AGFigure(rid,cid);
			agf.put(getRandomString(10), agg);
		}
		if(rel.equals("GL")){
			GLFigure glg=new GLFigure(rid,cid);
			glf.put(getRandomString(10), glg);
		}
		if(rel.equals("DP")){
			DPFigure dpg=new DPFigure(rid,cid);
			dpf.put(getRandomString(10), dpg);
		}
		if(rel.equals("AGr")){
			AGFigure agg=new AGFigure(cid,rid);
			agf.put(getRandomString(10), agg);
		}
		if(rel.equals("DPr")){
			DPFigure dpg=new DPFigure(cid,rid);
			dpf.put(getRandomString(10), dpg);
		}
		if(rel.equals("GLr")){
			GLFigure glg=new GLFigure(cid,rid);
			glf.put(getRandomString(10), glg);
		}
	}
	
	
	
	//随机生成序列码
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   


	

}
