<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="../layoutit/src/css/bootstrap.min.css" rel="stylesheet">
    <link href="../layoutit/src/css/style.css" rel="stylesheet">
<title>Download</title>
</head>
<body>
<s:include value="head.jsp"></s:include>
<div class="container-fluid">
<h2>The Existed Files in Server</h2>

<s:form action="CheckFileList" theme="simple">
		<table class="table">
			<tr class="info">
				<td>Check file list:<s:submit value="Check" /></td>
			</tr>
		</table>
	</s:form>
	
<s:property value="result" escape="false"></s:property>
</div>
<s:include value="bottom.html"></s:include>
</body>
</html>