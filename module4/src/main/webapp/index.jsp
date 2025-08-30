<!--
	Dan Zhu
	CSD-430
	Module 4.2
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="myBean" class="beanCollections.MyBean" scope="page"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Module 4 Assignment 4.2</title>
</head>
<body>
	<h1>Here is a table displaying informations about places that I want to visit.</h1>
	<table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>City</th>
                <th>State</th>
                <th>Country</th>
                <th>Population</th>
                <th>Attraction</th>
            </tr>
        </thead>
        <tbody>	
            <%
                String[] cities = myBean.getCities();
                String[] states = myBean.getStates();
                String[] countries = myBean.getCountries();
                String[] populations = myBean.getPopulations();
                String[] attractions = myBean.getAttractions();
                
                for(int i = 0; i < cities.length; i++) {
            %>
                <tr>
                    <td><%= cities[i] %></td>
                    <td><%= states[i] %></td>
                    <td><%= countries[i] %></td>
                    <td><%= populations[i] %></td>
                    <td><%= attractions[i] %></td>
                </tr>
            <%
                }
            %>
        </tbody>
   </table>
</body>
</html>