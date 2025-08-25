<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Application Form</title>
</head>
<body>
<h1>Job Application Form</h1>
<form action="response.jsp" method="POST">
	<table>
            <tr>
                <td><label for="firstName">First Name:</label></td>
                <td><input type="text" id="firstName" name="firstName" placeholder="Enter your first name" required></td>
            </tr>
            <tr>
                <td><label for="lastName">Last Name:</label></td>
                <td><input type="text" id="lastName" name="lastName" placeholder="Enter your last name" required></td>
            </tr>
            <tr>
                <td><label for="email">Email Address:</label></td>
                <td><input type="email" id="email" name="email" placeholder="Enter your email address" required></td>
            </tr>
            <tr>
                <td><label for="phone">Phone Number:</label></td>
                <td><input type="tel" id="phone" name="phone" placeholder="Enter your phone number"></td>
            </tr>
            <tr>
                <td><label for="formerEmployer">Former Employer:</label></td>
                <td><input type="text" id="formerEmployer" name="formerEmployer" placeholder="Enter your former employer"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: right;">
                    <input type="submit" value="Submit">
                </td>
            </tr>
        </table>
</form>
</body>
</html>