<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CountCorrelation</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
<h2>Count the Correlation and Relationship between classes in the Class Diagram</h2>
<s:include value="showuml.jsp"></s:include>
	<s:form action="CountCor" theme="simple">
		<table class="table table-bordered">
			<tr class="info">
			<td><s:submit value="Count" />(Please submit the class diagram firstly)</td>
			</tr>
		</table>
	</s:form>


	<table class="table" border=1>
			<tr>
			<td>Relationship Type</td>
			<s:iterator value="{'Association','Aggregation','Dependency','Generalization',
			'AggregationReverse','DependencyReverse','GeneralizationReverse'}"
				id="rtype">
				<td><s:property value="rtype" /></td>
			</s:iterator>
		</tr>
		<tr>
			<td>Relationship Shorthands</td>
			<s:iterator value="{'AS','AG','DP','GL','AGr','DPr','GLr'}"
				id="rtype">
				<td><s:property value="rtype" /></td>
			</s:iterator>
		</tr>
		<tr>
			<td>Relationship Strength</td>
			<s:iterator value="{'0.5','0.7','0.8','1.0','0.7','0.8','1.0'}"
				id="rtype">
				<td><s:property value="rtype" /></td>
			</s:iterator>
		</tr>
	</table>
<h4>The strength means the importance of the relationship, for example, generalization is more important 
to association.
</h4>


	<h4 class="text-primary">The correlation matrix is:</h4>
	<p>The correlation matrix shows the correlation between classes, for example, the element A[i][j] 
	in matrix A means the correlation between class i and class j. Class i is highly correlated to class j
	if A[i][j]>0.5.
	</p>


	<table class="table" border=1>
	
		<tr>
		<td>None</td>
			<s:iterator value="node" status="rowstatus">
				<s:iterator value="node[#rowstatus.index]">
					<td><s:property /></td>
				</s:iterator>
			</s:iterator>
		</tr>

		<s:iterator value="corr" status="rowstatus">
				<tr>
					<s:iterator value="node[#rowstatus.index]">
						<td><s:property /></td>
					</s:iterator>
					<s:iterator value="corr[#rowstatus.index]">
						<td><s:property /></td>
					</s:iterator>
				</tr>
			</s:iterator>

	</table>


	<h4 class="text-primary">The relationship matrix is:</h4>
<p>The relationship matrix shows the direct relationship between classes, for example, the element A[i][j] 
	in matrix A means the relationship between class i and class j. The relationship A[i][j] satisfies the 
	following two conditions.<br>
	(1) The reliability of the relationship is the highest<br>
	(2) The rank of the relationship is the biggest if the reliability is equal.
	</p>

	<table class="table" border=1>
	
		<tr>
		<td>None</td>
			<s:iterator value="node" status="rowstatus">
				<s:iterator value="node[#rowstatus.index]">
					<td><s:property /></td>
				</s:iterator>
			</s:iterator>
		</tr>

		<s:iterator value="rel" status="rowstatus">
				<tr>
					<s:iterator value="node[#rowstatus.index]">
						<td><s:property /></td>
					</s:iterator>
					<s:iterator value="rel[#rowstatus.index]">
						<td><s:property /></td>
					</s:iterator>
				</tr>
			</s:iterator>

	</table>


</div>
	<br><hr>
	<s:include value="bottom.html"></s:include>
</body>
</html>