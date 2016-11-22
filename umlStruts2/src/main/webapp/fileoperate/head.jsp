<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
     <%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>head</title>

    <meta name="author" content="Huangliang">

    <link href="${pageContext.request.contextPath}/layoutit/src/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/layoutit/src/css/style.css" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-2">
			<img class="img-rounded" alt="Bootstrap Image Preview" src="
			${pageContext.request.contextPath}/img/logo.jpg">
		</div>
		<div class="col-md-6">
			<h3 class="text-center text-primary" style="font-size: 60px">
				UML Analyzer
			</h3>
		</div>
		<div class="col-md-2">
			 
			<address>
				 <strong>Address</strong><br> Hai Nan University<br> Haikou, China<br><Strong>Contact</Strong>
				 <br>Duan Yucong  
				 <br>Huang Liang P:18789062337
			</address>
		</div>
		<div class="col-md-2">
			<img class="img-rounded" alt="Bootstrap Image Preview" src="
			${pageContext.request.contextPath}/img/hainu.jpg">
		</div>
	</div>

		<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default navbar-static-top navbar-inverse" role="navigation">
				<div class="navbar-header">
					 
					<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="${pageContext.request.contextPath}/fileoperate/default.jsp">Home</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						
						<li>
							<a href="${pageContext.request.contextPath}/fileoperate/upload.jsp">Upload</a>
						</li>
						<li class="dropdown">
							 <a class="dropdown-toggle" href="#" data-toggle="dropdown">FindCirculations<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/findpath.jsp">FindPaths</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/findcirculation.jsp">FindCirculations</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/abstractpath.jsp">AbstractPaths</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							 <a class="dropdown-toggle" href="#" data-toggle="dropdown">GetBigPicture<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/countcorrelation.jsp">CountCorrelations</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/countclassrank.jsp">CountClassRanks</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/sortclass.jsp">SortClasses</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/fileoperate/getbigpicture.jsp">GetBigPIcture</a>
								</li>
								
							</ul>
						</li>
						<li >
							 <a href="${pageContext.request.contextPath}/fileoperate/download.jsp">DownLoad</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/fileoperate/helpdocument.jsp">HelpDocument</a>
						</li>
					</ul>
					
				</div>
				
			</nav>
		</div>
	</div>
	
</div>

    <script src="${pageContext.request.contextPath}/layoutit/src/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/layoutit/src/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/layoutit/src/js/scripts.js"></script>
  </body>
</html>