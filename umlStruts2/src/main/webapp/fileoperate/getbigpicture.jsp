<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
        <%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jsuml2/Installation-Public/build/css/UDStyle.css" media="screen" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsuml2/Installation-Public/build/UDCore.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsuml2/Installation-Public/build/UDModules.js"></script>
<title>GetBigPicture</title>

	<script type="text/javascript">
	function diagram(){
		var classDiagram = new UMLClassDiagram({
					id : 'classDiagram',
					width : 1340,
					height : 600
				});	
		var classes = document.getElementById("classes").value;
				if (classes != "") {
					var tt = eval("(" + classes + ")");
					//alert(tt);
					//用于保存class，用于连线
					var classtemp = {}
					//创建class
					for ( var k in tt) {
						var jt = tt[k];
						//已经获取到类JSON的对象了
						var cfi = new UMLClass({
							x : jt.x,
							y : jt.y
						});
						cfi.setName(jt.name);
						for (var i = 0; i < jt.attribute.length; i++) {
							cfi.addAttribute(jt.attribute[i]);
						}
						for (var i = 0; i < jt.operation.length; i++) {
							cfi.addOperation(jt.operation[i]);
						}
						classDiagram.addElement(cfi);
						var key = jt.id;
						//alert(key);
						classtemp[key] = cfi;
					}
				}
		
		//连Association关系
				var assr = document.getElementById("ass").value;
				if (assr != "") {
					var at = eval("(" + assr + ")");
					//alert(at);
					for ( var k in at) {
						var asj = at[k];
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == at[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == at[k].destid) {
								dest = classtemp[j];
							}
						}
						var association = new UMLAssociation({
							a : source,
							b : dest
						});
						classDiagram.addElement(association);
					}
				}

				//连泛化关系
				var glr = document.getElementById("gl").value;
				if (glr != "") {
					var gat = eval("(" + glr + ")");
					//alert(gat);
					for ( var k in gat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == gat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == gat[k].destid) {
								dest = classtemp[j];
							}
						}
						var generalization = new UMLGeneralization({
							a : source,
							b : dest
						});
						classDiagram.addElement(generalization);
					}
				}

				//连聚集关系
				var aggr = document.getElementById("agg").value;
				if (aggr != "") {
					var aat = eval("(" + aggr + ")");
					//alert(aat);
					for ( var k in aat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == aat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == aat[k].destid) {
								dest = classtemp[j];
							}
						}
						var aggregation = new UMLAggregation({
							a : dest,
							b : source
						});
						classDiagram.addElement(aggregation);
					}
				}

				//连依赖关系
				var dpr = document.getElementById("dp").value;
				//alert(dpr);
				if (dpr != "") {//确保传入值不为空串
					//alert("yes");
					var dpat = eval("(" + dpr + ")");
					//alert(dpat);
					for ( var k in dpat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == dpat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == dpat[k].destid) {
								dest = classtemp[j];
							}
						}
						var dependency = new UMLDependency({
							a : source,
							b : dest
						});
						classDiagram.addElement(dependency);
					}
				}

				//连组合关系
				var cpr = document.getElementById("cp").value;
				//alert(dpr);
				if (cpr != "") {//确保传入值不为空串
					//alert("yes");
					var cpat = eval("(" + cpr + ")");
					//alert(dpat);
					for ( var k in cpat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == cpat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == cpat[k].destid) {
								dest = classtemp[j];
							}
						}
						var composition = new UMLComposition({
							a : dest,
							b : source
						});
						classDiagram.addElement(composition);
					}
				}

				//Draw the diagram
				classDiagram.draw();

				//Interaction is possible (editable)
				classDiagram.interaction(true);


		}

	function diagram1(){
		var classDiagram = new UMLClassDiagram({
					id : 'classDiagram1',
					width : 1340,
					height : 600
				});	
		var classes = document.getElementById("classes1").value;
				if (classes != "") {
					var tt = eval("(" + classes + ")");
					//alert(tt);
					//用于保存class，用于连线
					var classtemp = {}
					//创建class
					for ( var k in tt) {
						var jt = tt[k];
						//已经获取到类JSON的对象了
						var cfi = new UMLClass({
							x : jt.x,
							y : jt.y
						});
						cfi.setName(jt.name);
						for (var i = 0; i < jt.attribute.length; i++) {
							cfi.addAttribute(jt.attribute[i]);
						}
						for (var i = 0; i < jt.operation.length; i++) {
							cfi.addOperation(jt.operation[i]);
						}
						classDiagram.addElement(cfi);
						var key = jt.id;
						//alert(key);
						classtemp[key] = cfi;
					}
				}
		
		//连Association关系
				var assr = document.getElementById("ass1").value;
				if (assr != "") {
					var at = eval("(" + assr + ")");
					//alert(at);
					for ( var k in at) {
						var asj = at[k];
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == at[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == at[k].destid) {
								dest = classtemp[j];
							}
						}
						var association = new UMLAssociation({
							a : source,
							b : dest
						});
						classDiagram.addElement(association);
					}
				}

				//连泛化关系
				var glr = document.getElementById("gl1").value;
				if (glr != "") {
					var gat = eval("(" + glr + ")");
					//alert(gat);
					for ( var k in gat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == gat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == gat[k].destid) {
								dest = classtemp[j];
							}
						}
						var generalization = new UMLGeneralization({
							a : source,
							b : dest
						});
						classDiagram.addElement(generalization);
					}
				}

				//连聚集关系
				var aggr = document.getElementById("agg1").value;
				if (aggr != "") {
					var aat = eval("(" + aggr + ")");
					//alert(aat);
					for ( var k in aat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == aat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == aat[k].destid) {
								dest = classtemp[j];
							}
						}
						var aggregation = new UMLAggregation({
							a : dest,
							b : source
						});
						classDiagram.addElement(aggregation);
					}
				}

				//连依赖关系
				var dpr = document.getElementById("dp1").value;
				//alert(dpr);
				if (dpr != "") {//确保传入值不为空串
					//alert("yes");
					var dpat = eval("(" + dpr + ")");
					//alert(dpat);
					for ( var k in dpat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == dpat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == dpat[k].destid) {
								dest = classtemp[j];
							}
						}
						var dependency = new UMLDependency({
							a : source,
							b : dest
						});
						classDiagram.addElement(dependency);
					}
				}

				//连组合关系
				var cpr = document.getElementById("cp1").value;
				//alert(dpr);
				if (cpr != "") {//确保传入值不为空串
					//alert("yes");
					var cpat = eval("(" + cpr + ")");
					//alert(dpat);
					for ( var k in cpat) {
						var source;
						var dest;
						for ( var j in classtemp) {
							//alert(j);
							if (j == cpat[k].sourceid) {
								//alert(j);
								source = classtemp[j];
							}
							if (j == cpat[k].destid) {
								dest = classtemp[j];
							}
						}
						var composition = new UMLComposition({
							a : dest,
							b : source
						});
						classDiagram.addElement(composition);
					}
				}

				//Draw the diagram
				classDiagram.draw();

				//Interaction is possible (editable)
				classDiagram.interaction(true);


		}
	
		window.onload = function() {
			var diag=new diagram();
			var diag1=new diagram1();
		}
	</script>

</head>
<body>
<s:include value="head.jsp"></s:include>
<div class="container-fluid">

<h2>Get the Big Picture of the Class Diagram</h2>
<div id="classDiagram"></div>
<s:form action="GetBig1" theme="simple">
		<table class="table table-bordered">
			<tr class="info">
				<td>Input the Abstract level (From 1 to 5)
				<select name="level">
					<option value="1">level 1</option>
					<option value="2">level 2</option>
					<option value="3">level 3</option>
					<option value="4">level 4</option>
					<option value="5">level 5</option>
				</select>
				<s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
<s:form action="GetBig2" theme="simple">
		<table class="table table-bordered">
			<tr class="info">
				<td>Input the number of remained classes (small than the total number of classes)
				<select name="level">
					<option value="1"> 1</option>
					<option value="2"> 2</option>
					<option value="3"> 3</option>
					<option value="4"> 4</option>
					<option value="5"> 5</option>
					<option value="6"> 6</option>
					<option value="7"> 7</option>
					<option value="8"> 8</option>
					<option value="9"> 9</option>
					<option value="10"> 10</option>

				</select>
				<s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
<s:form action="GetBig" theme="simple">
		<table class="table table-bordered">
			<tr class="info">
				<td>Default abstract
				<s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
	

<input type="hidden" id="classes"  value="<s:property value="#session.classes"/>"/>
<input type="hidden" id="ass"  value="<s:property value="#session.ass"/>"/>
<input type="hidden" id="agg"  value="<s:property value="#session.agg"/>"/>
<input type="hidden" id="gl"  value="<s:property value="#session.gl"/>"/>
<input type="hidden" id="dp"  value="<s:property value="#session.dp"/>"/>
<input type="hidden" id="cp"  value="<s:property value="#session.cp"/>"/>

<input type="hidden" id="classes1"  value="<s:property value="#session.classes1"/>"/>
<input type="hidden" id="ass1"  value="<s:property value="#session.ass1"/>"/>
<input type="hidden" id="agg1"  value="<s:property value="#session.agg1"/>"/>
<input type="hidden" id="gl1"  value="<s:property value="#session.gl1"/>"/>
<input type="hidden" id="dp1"  value="<s:property value="#session.dp1"/>"/>
<input type="hidden" id="cp1"  value="<s:property value="#session.cp1"/>"/>


<div id="classDiagram1"></div>
</div>
<s:include value="bottom.html"></s:include>
</body>
</html>