<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
<title>FindCirculation</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
	<h2>Find the Circulation Contains the Given Class</h2>
	<s:include value="showuml.jsp"></s:include>
	<s:form action="FindCir" theme="simple">
		<table class="table">
			<tr class="info">
				<td>Input the class name that to search circulation:<s:textfield
						name="ckclass" /></td>
			</tr>
			<tr class="info">
				<td><s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
	<h4 class="text-primary">The circulations that contains the class
		are:</h4>
	<p class="text-success">
		<s:property value="cirresult" escape="false" />
	</p>
	<br><hr>
	</div>
	<s:include value="bottom.html"></s:include>
</body>
</html>