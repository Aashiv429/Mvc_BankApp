<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome</title>
</head>
<body>
	<h1 align="center">Welcome to the Bank</h1>
	<%
	session = request.getSession();
	String s1 = (String) session.getAttribute("cust_name");
	out.println(s1 +" "+ "Welcome to your account. Please select an operation to perform.");
	%>
	<br>
	<br>
	<a href="CheckBalance">1. Check Balance</a><br><br>
	<a href="changePin.html">2. Change PIN</a><br><br>
	<a href="loan.html">3. Apply Loan</a><br><br>
	<a href="transfer.html">4. Transfer Amount</a><br><br>
	<a href="TransactionHistory.jsp">5. View Transaction History</a><br><br>
	<a href="Logout">6. Logout</a>
</body>
</html>