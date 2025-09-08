<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Update Movie</h1>

	<jsp:useBean id="myDB" class="database.DbBean" scope="page" />
	<a href="index_02.jsp">back to index02</a>
	<br />

	<%
	if ("GET".equalsIgnoreCase(request.getMethod())) {
		// Show the form with existing movie data
		String movieId = request.getParameter("movieId");
		if (movieId != null) {
			// Fetch existing data to populate the form
			String movieData = myDB.getMovieDetails(movieId);
			out.print(movieData);
		} else {
			out.print("Please select a movie to update.");
		}
	} else if ("POST".equalsIgnoreCase(request.getMethod())) {
		// Handle the form submission
		String movieId = request.getParameter("movieId");
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
					message = myDB.createMovie(title, director, releaseYear != null ? publishYear : 0, genre = "---", Float.parseFloat(rating));
			} catch (NumberFormatException e) {
		message = "Year must be a valid number if provided.";
			}
		}

		// Print the result message after updating
		out.println("<br />");
		out.print(message);

		// Optionally, show the updated record
		if ("Movie updated successfully".equals(message)) {
			out.println("<br />");
			out.print(myDB.getMovieDetails(movieId)); // Show updated details
		}
	}
	%>

	<form method="GET" action="CRUD_Update.jsp">
		Movie ID: <select name="movieId">
			<%=myDB.getMovieIdOptions()%>
			<!-- Assuming this method fetches the movie IDs -->
		</select> <input type="submit" value="Select A Movie" />
	</form>


</body>
</html>