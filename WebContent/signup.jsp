<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign Up Page</title>
		<script src="https://www.google.com/recaptcha/api.js"></script>
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
		<div class="status-entry color6 center">
			<h1>Sign Up</h1>
		</div>
		<div  id="output">
			<p style="color: #ff0000; background-color: #202040;" class="" id="error">${errors}</p>
			<br/>
			<p style="color: #00ff00; background-color: #202040;" id="success">${success}</p>
		</div>
		<form action="SignUp" method="POST" class="center">
			<div class="status-entry center color6">
				<table>
					<tr>
						<td>
							<table class="center">
								<tr>
									<td>Display Name</td>
									<td>
										<input class="dark1" name="display" type="text">
									</td>
								</tr>
								<tr>
									<td>Username</td>
									<td>
										<input class="dark1" name="username" type="text">
									</td>
								</tr>
								<tr>
									<td> Password </td>
									<td>
										<input class="dark1" name="password1" type="password">
									</td>
								</tr>
								<tr>
									<td> Confirm Password </td>
									<td>
										<input class="dark1" name="password2" type="password">
									</td>
								</tr>
								
							</table>
						</td>
						<td>
							<div class="g-recaptcha center" data-sitekey="6LfhI9IUAAAAAPXoO5hndk889uUJ6wfzeOEXAGw8"></div>
						</td>
						<td style="color: #ff0000; background-color: #202040; max-width: 250px;">
						I would highly suggest that if you decide to sign up for this site, you
						 use a password which you have not used before. This site is still a WIP,
						  so there may be a multitude of vulnerabilities, some of which could end up exposing
						  your password. 
						</td>
					</tr>
				</table>
			</div>
			<button type="submit" class="button color6" value="Submit">SignUp</button>
		</form>
		<div class="status-entry color6 center">
		<a href="index.jsp">Home</a>
		</div>
	</body>
</html>