<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application Summary</title>
</head>
<%
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	String formerEmployer = request.getParameter("formerEmployer");
%>
<body>
	<h1>Application Summary</h1>
	 <table border="1" cellpadding="8" cellspacing="0">
        <tr><th colspan="2">Your Response</th></tr>
        <tr><td>First Name</td><td><%= firstName %></td></tr>
        <tr><td>Last Name</td><td><%= lastName %></td></tr>
        <tr><td>Email</td><td><%= email %></td></tr>
        <tr><td>Phone</td><td><%= phone %></td></tr>
        <tr><td>Former Employer</td><td><%= formerEmployer %></td></tr>
    </table>
    <h2>Thank you for interesting in this Job! We will get back to you as soon as possible.</h2>
</body>
</html>