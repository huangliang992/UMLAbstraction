<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Page</title>
</head>
<body>
	<s:include value="../fileoperate/head.jsp"></s:include>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<div class="jumbotron">
					<h2>Hello, ${sessionScope.user.username}!</h2>
					<p>This is a your home page, you can check the file you
						uploaded here, and also you can upload new files.</p>
					<p>
						<a class="btn btn-primary btn-large" href="#">Learn more</a>
					</p>
				</div>
			</div>
			<div class="col-md-4">
				username:${sessionScope.user.username}<br>
				password:${sessionScope.user.password}<br>
				email:${sessionScope.user.email}<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<s:form action="CheckFile">
					<h2>Check your file list</h2>
					<s:submit />
				</s:form>
				<table class="table" border=1>
				<tr class="success">
					<td>File Name</td>
					<td>File Type</td>
					<td>File Describe</td>
					<td>File path</td>
					<td>File Download</td>
				</tr>
				<s:property value="fileresult" escape="false"></s:property>
				</table>
			</div>
		</div>
		<s:property value="fy" escape="false"></s:property>
		<div class="row">
			<div class="col-md-12">
				<h2>Upload a new file (If no file uploaded, please upload a file firstly)</h2>
				<form role="form" action="UploadInfo" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						
						<label for="exampleInputFile"> File input </label> <input
							type="file" id="InputFile" name="file" />
						<p class="help-block">You can upload a file here.</p>
					</div>
					<div class="form-group">

						<label for="Input"> File type(example education,
							entertainment) </label> <input class="form-control" id="filetype"
							name="fb.type" />
					</div>
					<div class="form-group">

						<label for="describe"> Describe </label>
						<textarea rows="4" class="form-control" id="describe"
							name="fb.fdescribe"></textarea>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</div>
	<s:include value="../fileoperate/bottom.html"></s:include>
</body>
</html>