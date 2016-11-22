<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../layoutit/src/css/bootstrap.min.css" rel="stylesheet">
    <link href="../layoutit/src/css/style.css" rel="stylesheet">
<title>Sign Up</title>
</head>
<body>
<s:include value="../fileoperate/head.jsp"></s:include>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<form class="form-horizontal" role="form" action="Sign" >
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">
						Name
					</label>
					<div class="col-sm-4">
						<input class="form-control" name="user.username"/>
					</div>
					<div class="col-sm-6">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">
						Email
					</label>
					<div class="col-sm-4">
						<input  type="email" class="form-control" id="inputEmail3" name="user.email"/>
					</div>
					<div class="col-sm-6">
					</div>
				</div>
				<div class="form-group">
					 
					<label for="inputPassword3" class="col-sm-2 control-label">
						Password
					</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" id="inputPassword0" name="user.password"/>
					</div>
					<div class="col-sm-6">
					</div>
					
				</div>
				<div class="form-group">
					 
					<label for="inputPassword3" class="col-sm-2 control-label">
						Confirm Password
					</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" id="inputPassword1" />
					</div>
					<div class="col-sm-6">
					</div>
					
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							 
							<label>
								<input type="checkbox" /> Remember me
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 
						<button type="submit" class="btn btn-default">
							Sign in
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<s:include value="../fileoperate/bottom.html"></s:include>
<!-- 
you are<s:property value="user.username"></s:property>
<br>${sessionScope.user.username} 
<br>${requestScope.user.username}
<br>${user.username}
-->
    <script src="../layoutit/src/js/jquery.min.js"></script>
    <script src="../layoutit/src/js/bootstrap.min.js"></script>
    <script src="../layoutit/src/js/scripts.js"></script>
</body>
</html>