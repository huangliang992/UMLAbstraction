<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload File</title>
</head>
<body>
<s:include value="head.jsp"></s:include>

<div class="container-fluid">
	<h2>Upload A Class Diagram File</h2>
	<s:include value="showuml.jsp"></s:include>
	<s:form action="Upload" method="post" enctype="multipart/form-data"
		theme="simple">
		<table class="table">
			<tr class="info">
				<td>Select a class diagram file and UploadFile<s:file
						name="upload" /></td>
			</tr>
			<tr class="info">
				<td><s:submit value="submit" /></td>
			</tr>
		</table>
	</s:form>
	</div>
	<s:include value="uploadsuccess.jsp"></s:include>
	<s:include value="bottom.html"></s:include>
</body>
</html>