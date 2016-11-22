<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CountClassRank</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
<h2>Count the class rank in the Class Diagram according to a algorithm similar to page rank</h2>
<s:include value="showuml.jsp"></s:include>
	<s:form action="CountRank" theme="simple">
		<table class="table table-bordered">
			<tr class="info">
			<td><s:submit value="CountRank" />(Please submit the class diagram firstly)</td>
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
			<td>Relationship Rank</td>
			<s:iterator value="{'5.0','7.0','8.0','10.0','7.0','8.0','10.0'}"
				id="rtype">
				<td><s:property value="rtype" /></td>
			</s:iterator>
		</tr>
	</table>
<h4>The relationship rank means the importance of the relationship, for example, generalization is more important 
to association.
</h4>

	<h4 class="text-primary">The transition probability matrix is:</h4>
	<p>The transition probability matrix shows the transition probability of class rank from one class to 
	its adjacent class, for example, the element A[i][j] 
	in matrix A means the transition probability of class i's rank to class j's rank. 
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

		<s:iterator value="tranmatrix" status="rowstatus">
				<tr>
					<s:iterator value="node[#rowstatus.index]">
						<td><s:property /></td>
					</s:iterator>
					<s:iterator value="tranmatrix[#rowstatus.index]">
						<td><s:property /></td>
					</s:iterator>
				</tr>
			</s:iterator>

	</table>


	<h4 class="text-primary">The final class rank  matrix is:</h4>
<p>The final class rank matrix shows the final class rank of each class. The class is more important if
its final class rank is higher. For example, class A is more important than class B if the rank of 
class A is 200 and the rank of class B is 100<br>
	</p>

	<table class="table" border=1>
		<tr>
			<td>Class</td>
			<s:iterator value="node" status="rowstatus">
				<s:iterator value="node[#rowstatus.index]">
					<td><s:property /></td>
				</s:iterator>
			</s:iterator>
		</tr>
		<tr>
			<td>Rank</td>
			<s:iterator value="rank" status="rowstatus">
				<s:iterator value="rank[#rowstatus.index]">
					<td><s:property /></td>
				</s:iterator>
			</s:iterator>
		</tr>
	</table>

	<h4 class="text-primary">The iterator process is:</h4>
<p>The iterator process shows the process of calculate the class rank<br>
	</p>
<p class="text-success"><s:property value="iterprocess" escape="false"/></p>
	<br><hr>
</div>	
<s:include value="bottom.html"></s:include>
</body>

</html>