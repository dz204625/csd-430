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
		// Call the populateLibrary method from DbBean
		result = myDB.populateTable(); 
	} catch (Exception e) {
		result = "An error occurred: " + e.getMessage();
	}
	%>
	<%=result%>

</body>
</html>