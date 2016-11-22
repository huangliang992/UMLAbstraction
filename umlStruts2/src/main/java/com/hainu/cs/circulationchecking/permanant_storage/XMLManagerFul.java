package com.hainu.cs.circulationchecking.permanant_storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hainu.cs.circulationchecking.entity.AGFigure;
import com.hainu.cs.circulationchecking.entity.ASFigure;
import com.hainu.cs.circulationchecking.entity.CPFigure;
import com.hainu.cs.circulationchecking.entity.ClassFigure;
import com.hainu.cs.circulationchecking.entity.DPFigure;
import com.hainu.cs.circulationchecking.entity.GLFigure;
/**
 * 将uml中的完整信息提取出来，包括类的属性和方法，还有类的坐标，XMLManager没有将坐标和属性方法
 * 等信息提取出来。这个类在后面操作类图抽象时要用到
 * @author hl
 *
 */
public class XMLManagerFul {
	private HashMap<String,ClassFigure> cd=new HashMap<String,ClassFigure>();
	private HashMap<String,ASFigure> asd=new HashMap<String,ASFigure>();
	private HashMap<String,AGFigure> agd=new HashMap<String,AGFigure>();
	private HashMap<String,CPFigure> cpd=new HashMap<String,CPFigure>();
	private HashMap<String,GLFigure> gld=new HashMap<String,GLFigure>();
	private HashMap<String,DPFigure> dpd=new HashMap<String,DPFigure>();
	
	public void init(String filename) throws Exception{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.parse(filename);
			Element root=doc.getDocumentElement();
			String expression="/uml/XMI/XMI.content/*[local-name()='Model']/*[local-name()='Namespace.ownedElement']";
			String expression1="/uml/pgml";
			XPathFactory xf=XPathFactory.newInstance();
			XPath xp=xf.newXPath();
				Element namespace=(Element) xp.evaluate(expression, doc, XPathConstants.NODE);
				Element pgml=(Element) xp.evaluate(expression1, doc, XPathConstants.NODE);
				System.out.println(namespace.getNodeName());
				for(Node node=namespace.getFirstChild();node!=null;node=node.getNextSibling()){
					if(node instanceof Element){
						if(node.getNodeName().equals("UML:Class")){
							String id=node.getAttributes().getNamedItem("xmi.id").getNodeValue();
							String name=node.getAttributes().getNamedItem("name").getNodeValue();
							ArrayList<String> at=new ArrayList<String>();
							ArrayList<String> op=new ArrayList<String>();
							for(Node node1=node.getFirstChild();node1!=null;node1=node1.getNextSibling()){
								if(node1 instanceof Element){
									if(node1.getNodeName().equals("UML:Classifier.feature")){
										NodeList attrlist=((Element) node1).getElementsByTagName("UML:Attribute");
										NodeList operlist=((Element) node1).getElementsByTagName("UML:Operation");
										
										for(int i=0;i<attrlist.getLength();i++){
											String a=attrlist.item(i).getAttributes().getNamedItem("name").getNodeValue();
											at.add(a);
										}
										for(int i=0;i<operlist.getLength();i++){
											String o=operlist.item(i).getAttributes().getNamedItem("name").getNodeValue();
											op.add(o);
										}
										
									}
									if(node1.getNodeName().equals("UML:Namespace.ownedElement")){
										NodeList list=((Element) node1).getElementsByTagName("UML:Dependency");
										Element node2=(Element) list.item(0);
										System.out.println(node2.getNodeName());
										if(node2!=null){
										String dpid=node2.getAttributes().getNamedItem("xmi.id").getNodeValue();
										NodeList list1=node2.getElementsByTagName("UML:Class");
										String sourceid=list1.item(0).getAttributes().getNamedItem("xmi.idref").getNodeValue();
										String destid=list1.item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue();
										dpd.put(dpid, new DPFigure(sourceid,destid));
										}
									}
								}
							}
							cd.put(id, new ClassFigure(id,name,at,op));
						}
						if(node.getNodeName().equals("UML:Association")){
							String id=node.getAttributes().getNamedItem("xmi.id").getNodeValue();
							NodeList list=((Element) node).getElementsByTagName("UML:AssociationEnd");
							NodeList list1=((Element) node).getElementsByTagName("UML:Class");
							String sourceid=list1.item(0).getAttributes().getNamedItem("xmi.idref").getNodeValue();
							String destid=list1.item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue();
							if(list.item(0).getAttributes().getNamedItem("aggregation").getNodeValue().equals("none")){
								asd.put(id, new ASFigure(sourceid,destid));
							}
							if(list.item(0).getAttributes().getNamedItem("aggregation").getNodeValue().equals("aggregate")){
								System.out.println("Aggregate");
								agd.put(id, new AGFigure(destid,sourceid));
							}
							if(list.item(0).getAttributes().getNamedItem("aggregation").getNodeValue().equals("composite")){
								cpd.put(id, new CPFigure(destid,sourceid));
							}
						}
						if(node.getNodeName().equals("UML:Generalization")){
							String id=node.getAttributes().getNamedItem("xmi.id").getNodeValue();
							NodeList list=((Element) node).getElementsByTagName("UML:Class");
							String sourceid=list.item(0).getAttributes().getNamedItem("xmi.idref").getNodeValue();
							String destid=list.item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue();
							gld.put(id, new GLFigure(sourceid,destid));
						}
					}
				}
				for (Map.Entry<String, ClassFigure> entry : cd.entrySet()) {
					String key = entry.getKey();
					ClassFigure value = entry.getValue();
					for (Node node = pgml.getFirstChild(); node != null; node = node.getNextSibling()) {
						if (node instanceof Element) {
							if (node.getAttributes().getNamedItem("href").getNodeValue().equals(key)) {
								NodeList list=((Element) node).getElementsByTagName("rectangle");
								Node node1=list.item(0);
								String x=node1.getAttributes().getNamedItem("x").getNodeValue();
								String y=node1.getAttributes().getNamedItem("y").getNodeValue();
								int x1=Integer.parseInt(x);
								int y1=Integer.parseInt(y);
								value.setX(x1);;
								value.setY(y1);
							}
						}
					}
				}
			}		
	public HashMap<String,ASFigure> getAS(){
		return this.asd;
	}
	public HashMap<String,AGFigure> getAG(){
		return this.agd;
	}
	public HashMap<String,CPFigure> getCP(){
		return this.cpd;
	}
	public HashMap<String,GLFigure> getGL(){
		return this.gld;
	}
	public HashMap<String,DPFigure> getDP(){
		return this.dpd;
	}
	public HashMap<String,ClassFigure> getCls(){
		return this.cd;
	}
	}

