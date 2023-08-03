<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pin</title>
</head>
<body>
<h3>
		<%
		session = request.getSession();
		String s1 = (String) session.getAttribute("cust_name");
		out.println("Dear" + s1 + "Your pin is updated successfully!");
		%>
	</h3>
	<a href="homePage.jsp">Redirect</a>
</body>
</html>