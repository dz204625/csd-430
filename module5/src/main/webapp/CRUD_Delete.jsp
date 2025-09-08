<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Delete Movie</h1>

	<jsp:useBean id="myDB" class="database.DbBean" scope="page" />
	<a href="index_02.jsp">back to index02</a>
	<br />
	<%
	if (request.getMethod().equals("GET")) {
		// Print the dropdown form options to delete a movie
		String value = myDB.formGetPK("DeleteMovie.jsp");
	}
	%>

	<%
	if (request.getMethod().equals("POST")) {
		// Get the selected movie ID as integer
		String movieId = request.getParameter("movieId");

		// Call delete method on bean
		out.print(myDB.delete(movieId));

		out.println("<br />");

	}
	%>

	<!-- Form for selecting movieId and submitting POST -->
	<form method="post">
		Movie ID: <select name="movieId">
			<%=myDB.getMovieIdOptions()%>
		</select> <input type="submit" value="Delete Movie" />
	</form>
	<!--  display updated table -->
	<%=myDB.readAll()%>
</body>
</html>