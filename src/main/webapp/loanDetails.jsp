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
		out.println("Your loan details: ");
		out.println("<br>Loan Id:  "+ session.getAttribute("l_id"));
		out.println("<br>loan type:   " +session.getAttribute("l_type"));
		out.println("<br>Tenure:   " +session.getAttribute("tenure"));
		out.println("<br>Interest:  "+session.getAttribute("interest"));
		out.println("<br>Description:  "+session.getAttribute("description"));
		%>
	</h3>
	<a href="homePage.jsp">Redirect</a>
</body>
</html>