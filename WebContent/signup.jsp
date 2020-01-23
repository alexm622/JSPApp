<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up Page</title>
<script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
	<p style="color: #ff0000" id="error">${errors}</p>
	<p style="color: #00ff00" id="success">${success}</p>
	
	<form action="SignUp" method="POST">
		<table>
			<tr>
				<td>Display Name</td>
				<td><input name="display" type="text"> </td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input name="username" type="text"> </td>
			</tr>
			<tr>
				<td> Password </td>
				<td><input name="password1" type="password"></td>
			</tr>
			<tr>
				<td> Confirm Password </td>
				<td><input name="password2" type="password"></td>
			</tr>
			
		</table>
		<div class="g-recaptcha" data-sitekey="6LfhI9IUAAAAAPXoO5hndk889uUJ6wfzeOEXAGw8"></div>
		<button type="submit" value="Submit">SignUp</button>
	</form>
</body>
</html>