<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AbstractPaths</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
	<h2>Abstract the Paths Between Two Classes and Get the Direct Relationship</h2>
	<s:include value="showuml.jsp"></s:include>
	<s:form action="AbsPath" theme="simple">
		<table class="table">
			<tr class="info">
				<td>Input the begin class <s:textfield name="origin" /></td>
			</tr>
			<tr class="info">
				<td>Input the end class <s:textfield name="dest" /></td>
			</tr>
			<tr class="info">
				<td><s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
	<h4 class="text-primary">The relationships between the begin class
		and end class are :</h4>
	<p class="text-success">
		<s:property value="absresult" escape="false"></s:property>
	</p>
	<br><hr>
</div>
<s:include value="bottom.html"></s:include>
</body>
</html>