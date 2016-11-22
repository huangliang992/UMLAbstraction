<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SortClasses</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
<h2>Sort the classes according to rank of classes</h2>
<s:include value="showuml.jsp"></s:include>
	<s:form action="SortClass" theme="simple">
		<table class="table">
			<tr class="info">
				<td><s:submit value="SortClass" /></td>
			</tr>
		</table>
	</s:form>
	<h4 class="text-primary">Sort the classes from big to small according to the final class rank:</h4>
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
			<s:iterator value="srank" status="rowstatus">
				<s:iterator value="srank[#rowstatus.index]">
					<td><s:property /></td>
				</s:iterator>
			</s:iterator>
		</tr>
	</table>
	</div>
	<s:include value="bottom.html"></s:include>
</body>
</html>