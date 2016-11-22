<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
	<h4 class="text-primary">
		Upload status:<span class="text-success"><s:property
				value="statu" /></span>
	</h4>
	<h4 class="text-primary">
		File Name:<span class="text-success"><s:property
				value="uploadFileName"></s:property></span>
	</h4>
	<br><hr>
	</div>
</body>
</html>