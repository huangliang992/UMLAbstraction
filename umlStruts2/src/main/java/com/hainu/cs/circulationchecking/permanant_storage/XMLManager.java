package com.hainu.cs.circulationchecking.permanant_storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hainu.cs.circulationchecking.entity.Classes;
import com.hainu.cs.circulationchecking.entity.Relationships;

public class XMLManager {
	private HashMap<String,Classes> cls=new HashMap<String,Classes>();
	private HashMap<String,Relationships> res=new HashMap<String,Relationships>();
	
	public void parse(String filepath){
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			try {
				Document doc=db.parse(filepath);
				Element root=doc.getDocumentElement();
				XPathFactory xf=XPathFactory.newInstance();
				XPath xp=xf.newXPath();
				String xmi="/uml/XMI/XMI.content/*[local-name()='Model']/*[local-name()='Namespace.ownedElement']";
				try {
					Element namespace=(Element) xp.evaluate(xmi,doc,XPathConstants.NODE );
					for(Node node=namespace.getFirstChild(); node!=null;node=node.getNextSibling()){
						if(node instanceof Element){
							// node of class and dependency included in class node
							if(node.getNodeName().equals("UML:Class")){
								String id=node.getAttributes().getNamedItem("xmi.id").getNodeValue();
								String name=node.getAttributes().getNamedItem("name").getNodeValue();
								//create the class object
								Classes cl=new Classes();
								cl.setId(id);
								cl.setName(name);
								cls.put(id, cl);
								for(Node node1=node.getFirstChild();node1!=null;node1=node1.getNextSibling()){
									if(node1 instanceof Element){
										if(node1.getNodeName().equals("UML:Namespace.ownedElement")){
											NodeList list=((Element) node1).getElementsByTagName("UML:Dependency");
											Element node2=(Element) list.item(0);
											if(node2!=null){
											String dpid=node2.getAttributes().getNamedItem("xmi.id").getNodeValue();
											NodeList list1=node2.getElementsByTagName("UML:Class");
											String sourceid=list1.item(0).getAttributes().getNamedItem("xmi.idref").getNodeValue();
											String destid=list1.item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue();
											//save the DP relationship
											Relationships re=new Relationships();
											re.setId(dpid);
											re.setType("DP");
											re.setFrom(sourceid);
											re.setTo(destid);
											res.put(dpid, re);
											}
										}
									}
								}
							}
							//node of association family
							if(node.getNodeName().equals("UML:Association")){
								String id=node.getAttributes().getNamedItem("xmi.id").getNodeValue();
								NodeList list=((Element) node).getElementsByTagName("UML:AssociationEnd");
								NodeList list1=((Element) node).getElementsByTagName("UML:Class");
								String sourceid=list1.item(0).getAttributes().getNamedItem("xmi.idref").getNodeValue();
								String destid=list1.item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue();
								if(list.item(0).getAttributes().getNamedItem("aggregation").getNodeValue().equals("none")){
									//save association
									Relationships re=new Relationships();
									re.setId(id);
									re.setType("AS");
									re.setFrom(sourceid);
									re.setTo(destid);
									res.put(id, re);
								}
								if(list.item(0).getAttributes().getNamedItem("aggregation").getNodeValue().equals("aggregate")){
									//save aggregation
									Relationships re=new Relationships();
									re.setId(id);
									re.setType("AG");
									re.setFrom(destid);
									re.setTo(sourceid);
									res.put(id, re);
								}
								if(list.item(0).getAttributes().getNamedItem("aggregation").getNodeValue().equals("composite")){
									//save composition
									Relationships re=new Relationships();
									re.setId(id);
									re.setType("CP");
									re.setFrom(destid);
									re.setTo(sourceid);
									res.put(id, re);
								}
							}
							//node of Generalization
							if(node.getNodeName().equals("UML:Generalization")){
								String id=node.getAttributes().getNamedItem("xmi.id").getNodeValue();
								NodeList list=((Element) node).getElementsByTagName("UML:Class");
								String sourceid=list.item(0).getAttributes().getNamedItem("xmi.idref").getNodeValue();
								String destid=list.item(1).getAttributes().getNamedItem("xmi.idref").getNodeValue();
								//save generalization
								Relationships re=new Relationships();
								re.setId(id);
								re.setType("GL");
								re.setFrom(sourceid);
								re.setTo(destid);
								res.put(id, re);
							}
						}
					}
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public HashMap<String,Classes> getClasses(){
		return this.cls;
	}
	public HashMap<String,Relationships> getRelationships(){
		return this.res;
	}
	
	public static void main(String[] args){
		String path="C:/Users/hl/Desktop/test.uml";
		XMLManager m=new XMLManager();
		m.parse(path);
		HashMap<String,Classes> cs=m.getClasses();
		HashMap<String,Relationships> rs=m.getRelationships();
		for(Map.Entry<String, Classes>entry:cs.entrySet()){
			Classes value=entry.getValue();
			System.out.println(value.getId()+" "+value.getName());
		}
		for(Map.Entry<String, Relationships>entry:rs.entrySet()){
			Relationships value=entry.getValue();
			System.out.println(value.getId()+" "+value.getType());
		}
	}
}
