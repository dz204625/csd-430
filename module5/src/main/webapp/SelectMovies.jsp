<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="myDB" class="database.DbBean" scope="page" />
	<a href="index.jsp">back to index</a>
	<br />

	<%
	String result = "";
	try {
		// Call the readAll() method from DbBean
		result = myDB.readAll(); // Assuming this method returns a table with all movie entries
	} catch (Exception e) {
		result = "Error: " + e.getMessage(); // Handle any errors
	}
	%>
	<table>
		<%=result%>
		<!-- Print the result (which should be the HTML table of all movies) -->
	</table>
</body>
</html>