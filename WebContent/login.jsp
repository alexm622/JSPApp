<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/style.css" type="text/css">
		<style type="text/css">
			a:link{
				color: #ca3e47;
			}
			
			a:visited{
				color:  #ff4d00;
			}
			
			a:hover {
			  color: hotpink;
			}
		</style>
	</head>
	<body class="color5">
		<div class="status-entry color6">
			<h1 class="center">Login</h1>
		</div>
		<div id="output">
			<p style="color: #ff0000; background-color: #202040;" class="" id="error">${issue}</p>
			<br/>
			<p style="color: #00ff00; background-color: #202040;" id="success">${result}</p>
		</div>
		<form action="Login" method="POST" class="center">
			<div class="status-entry center color6">
				<table class=" color6 c">
					<tr>
						<td>Username</td>
						<td>
							<input class="dark1" name="username" type="text">
						</td>
					</tr>
					<br/>
					<tr>
						<td> Password </td>
						<td>
							<input class="dark1" name="password" type="password">
						</td>
					</tr>
				</table>
			</div>
			<button type="submit" class="button color6" value="Submit">Login</button>
		</form>
	</body>
</html>