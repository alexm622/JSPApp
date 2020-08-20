<%@page import="com.alex.forums.threads.ThreadingUtils"%>
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
	<h1>
		Threads
	</h1>
	${Threads}
	<div>
	
		<form name="nxt" method="POST" action="Posts">	
		<input name="hidden" type="hidden" />
			<table>
				<tr>
					<th>
						<input type="button" name="back" value="back" class="button_threads dark1" onclick=${back}/>
					</th>
					<th>
						<input type="button"  name="next" value="next" class="button_threads dark1" onclick=${next}/>
					</th>
					<th>
					Items ${range}
					</th>
				</tr>
			</table>
		</form>
	</div>
	<div class="status-entry color6 center">
		<a href="login.jsp"> Login Page</a>
		| <a href="signup.jsp">Sign Up</a>
	</div>
</body>
</html>