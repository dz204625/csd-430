<html>
<head>
    <title>Add Movie</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Add Movie</h2>
<form action="MovieServlet" method="post">
    <input type="hidden" name="action" value="insert">
    Title: <input type="text" name="title"><br><br>
    Director: <input type="text" name="director"><br><br>
    Year: <input type="number" name="releaseYear"><br><br>
    Genre: <input type="text" name="genre"><br><br>
    Rating: <input type="text" name="rating"><br><br>
    <button type="submit">Save</button>
</form>
<a href="MovieServlet?action=read">Back to list</a>
</body>
</html>
