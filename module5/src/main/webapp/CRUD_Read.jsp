<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSP Create Table</h1>

	<!-- Use the jsp:useBean tag to instantiate DbBean -->
	<jsp:useBean id="myDB" class="database.DbBean" scope="page" />
	<a href="index.jsp">back to index</a>
	<br />

	<%
	try {
		// Call the createTable method and display the result
		out.print(myDB.createTable());
	} catch (Exception e) {
		out.print("Error creating table: " + e.getMessage());
	}
	%>
</body>
</html>