<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="s" uri="/struts-tags"%>
        <%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UMLAnalyzer</title>
</head>
<body>
	<s:include value="head.jsp"></s:include>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>
					UML Analyzer!
				</h2>
				<p>
					UML analyzer is a tool for analyzing the UML diagrams (now only for class diagram).<br>
					You can look the help document <a href="${pageContext.request.contextPath}/fileoperate/helpdocument.jsp">online</a> or download the
					pdf file <a href="${pageContext.request.contextPath}/fileoperate/download.jsp">here</a>. 
					You can also find the "example.uml" <a href="${pageContext.request.contextPath}/fileoperate/download.jsp">here</a>.
				</p>
				<p>
					You can upload a class diagram file, and quickly start.
					<a class="btn btn-primary btn-large" href="${pageContext.request.contextPath}/fileoperate/upload.jsp">Start</a>
				</p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<h2>
				Notice
			</h2>
			<p>
				The class diagram file you upload should be created by ArgoUML tool. 
				And the form of the file should be like "example.uml". We can only recognize the class
				diagram created in ArgoUML and the file should be
				saved as "xxx.uml". ArgoUML is a modeling tool like Rational Rose and Enterprise Architecture.
			</p>
		</div>
		<div class="col-md-4">
			<h2>
				Find Circulations
			</h2>
			<p>
				The part of find circulations is used to search and analysis the circulations in the class diagram
				. We find that circulations in a class diagram often contain problems, our work is to find
				and analyze them. 
			</p>
		</div>
		<div class="col-md-4">
			<h2>
				Get Big Picture
			</h2>
			<p>
				The part of get the big picture of a class diagram can help user quickly understand the class diagram.
				It shows the relationship and correlation between any two classes in the class diagram. At the same time
				, it gives a rank of each class which means the importance of the diagram. At last it generates the big picture of 
				the class diagram.
			</p>
		</div>
	</div>
</div>
<s:include value="bottom.html"></s:include>
</body>
</html>