<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Add new Movie</h1>

	<jsp:useBean id="myDB" class="database.DbBean" scope="page" />
	<a href="index_02.jsp">back to index02</a>
	<br />
	<%
	// Handling GET: show the form
	if (request.getMethod().equals("GET")) {
		out.println("<form method='POST'>");
		out.println("Title: <input type='text' name='title'><br>");
		out.println("Director: <input type='text' name='director'><br>");
		out.println("Release Year: <input type='text' name='releaseYear'><br>");
		out.println("Genre: <input type='text' name='genre'><br>");
		out.println("Rating: <input type='text' name='rating'><br>");

		out.println("<input type='submit' value='Add Movie'>");
		out.println("</form>");
	}

	// Handling POST: add new moview
	if (request.getMethod().equals("POST")) {
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String releaseYear = request.getParameter("releaseYear");
		String genre = request.getParameter("genre");
		String rating = request.getParameter("rating");

		String message = "";

		if (title == null || title.trim().isEmpty()) {
			message = "Title is required.";
		} else {
			Integer publishYear = null;
			try {
				if (releaseYear != null && !releaseYear.trim().isEmpty()) {
					publishYear = Integer.parseInt(releaseYear);
				}
		// Add the movie to the database
		message = myDB.createMovie(title, director, releaseYear != null ? publishYear : 0, genre = "---", Float.parseFloat(rating));
			} catch (NumberFormatException e) {
		message = "Release year or Rating must be a number.";
			}
		}
		out.println("<p>" + message + "</p>");
	}
	%>
</body>
</html>