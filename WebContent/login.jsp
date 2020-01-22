<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Login" method="POST">
		<table>
			<tr>
				<td>Username</td>
				<td><input name="username" type="text"> </td>
			</tr>
			<tr>
				<td> Password </td>
				<td><input name="password" type="password"></td>
			</tr>
		</table>
		<button type="submit" value="Submit">Login</button>
	</form>
</body>
</html>