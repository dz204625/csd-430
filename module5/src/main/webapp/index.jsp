<%@ page import="java.util.List, model.Movie" %>
<%
    String message = (String) request.getAttribute("message");
	List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    String action = request.getParameter("action");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Management Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Movie Management Dashboard</h1>

<!-- Menu -->
<ul class="menu">
    <li><a href="MovieServlet?action=reset">Create Table</a></li>
    <li><a href="MovieServlet?action=populate">Populate Table</a></li>
    <li><a href="MovieServlet?action=list">View All Movies</a></li>
</ul>
</body>
</html>
