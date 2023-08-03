<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>
		<%
		session = request.getSession();
		out.println("Your current balance is : " + session.getAttribute("balance"));
		%>
	</h3>
	<a href="homePage.jsp">Redirect</a>
</body>
</html>