<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">

<title>FindPath</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
	<h2>Find the Paths between the Two Given Classes</h2>
	<s:include value="showuml.jsp"></s:include>
	<s:include value="showuml.jsp"></s:include>
	<s:form action="FindPath" theme="simple">
		<table class="table table-bordered">
			<tr class="info">
				<td>Input the begin class in the class diagram Class 1
				<s:textfield name="begin" styleClass="input-small"/></td>
			</tr>
			<tr class="info">
				<td>Input the end class in the class diagram Class 2 
				<s:textfield name="end" /></td>
			</tr>
			<tr class="info">
			<td><s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
	<h4 class="text-primary">The paths between the begin class and end class are:</h4>
	<p class="text-success">
		<s:property value="result" escape="false"></s:property>
	</p>
	<br><hr>
	</div>
	<s:include value="bottom.html"></s:include>
</body>
</html>