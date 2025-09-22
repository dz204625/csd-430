<%@ page import="model.Movie" %>
<%
    Movie m = (Movie) request.getAttribute("movie");
%>
<html>
<head>
    <title>Edit Movie</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Edit Movie</h2>
<form action="MovieServlet" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="movieId" value="<%= m.getMovieId() %>">
    Title: <input type="text" name="title" value="<%= m.getTitle() %>"><br><br>
    Director: <input type="text" name="director" value="<%= m.getDirector() %>"><br><br>
    Year: <input type="number" name="releaseYear" value="<%= m.getReleaseYear() %>"><br><br>
    Genre: <input type="text" name="genre" value="<%= m.getGenre() %>"><br><br>
    Rating: <input type="text" name="rating" value="<%= m.getRating() %>"><br><br>
    <button type="submit">Update</button>
</form>
<a href="MovieServlet?action=read">Back to list</a>
</body>
</html>
