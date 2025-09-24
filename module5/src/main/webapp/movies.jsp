<%@ page import="java.util.List, model.Movie" %>
<%
	String message = (String) request.getAttribute("message");
    String action = (String) request.getParameter("action");
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>
<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Movie Management Dashboard</h1>

<!-- Menu -->
<ul>
    <li><a href="MovieServlet?action=reset">Create Table</a></li>
    <li><a href="MovieServlet?action=populate">Populate Table</a></li>
    <li><a href="MovieServlet?action=list">View All Movies</a></li>
    <% if (action.equals("list")) { %>
   	<li><a href="addMovie.jsp">Add New</a></li>
   	<li><a href="index.jsp">Back</a></li>
	<% } %>
</ul>

<!-- Message area -->
<% if (message != null) { %>
    <p style="color:green; font-weight:bold;"><%= message %></p> 
<% } %>

<% if (action.equals("list") && movies != null && !movies.isEmpty()) { %>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Director</th>
            <th>Year</th>
            <th>Genre</th>
            <th>Rating</th>
            <th>Actions</th>
        </tr>
        <% for (Object o : movies) {
               Movie m = (Movie) o; %>
        <tr>
            <td><%= m.getMovieId() %></td>
            <td><%= m.getTitle() %></td>
            <td><%= m.getDirector() %></td>
            <td><%= m.getReleaseYear() %></td>
            <td><%= m.getGenre() %></td>
            <td><%= m.getRating() %></td>
            <td>
                <a href="MovieServlet?action=edit&id=<%= m.getMovieId() %>">Edit</a> |
                <a href="MovieServlet?action=delete&id=<%= m.getMovieId() %>"
                   onclick="return confirm('Delete this movie?')">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
<% } %>
</body>
</html>
