<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--
	Dan Zhu
	CSD-430
	Module 2.2
 -->
 <!-- display data for table -->
<%
String[] cities = { "Denver", "San Diego", "New Orleans", "Nashville", "Chicago" };
String[] states = { "Colorado", "California", "Louisiana", "Tennessee", "Illinois" };
String[] attractions = { "Red Rocks Amphitheatre and Denver Botanic Gardens", "San Diego Zoo and Balboa Park",
		"French Quarter and St. Louis Cathedral", "Country Music Hall of Fame and Broadway",
		"Willis Tower Skydeck and Navy Pier" };
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSD-430</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1>Places I like to visit</h1>
	<p>The table will list places that I want to visit and its top
		attractions.</p>
	<table>
		<thead>
			<th>City</th>
			<th>State</th>
			<th>Attractions</th>
		</thead>
		<tbody>
			<%
                // Loop through the arrays and display each record
                for (int i = 0; i < cities.length; i++) {
            %>
			<tr>
				<td><%= cities[i] %></td>
				<td><%= states[i] %></td>
				<td><%= attractions[i] %></td>
			</tr> <% } %>
		</tbody>
	</table>
</body>
</html>