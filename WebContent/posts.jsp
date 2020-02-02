<%@page import="com.alex.forums.threads.ThreadingUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Posts</title>
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
		Posts
	</h1>
	${Posts}
	<div>
	
		<form name="nxt" method="POST" action="Posts">	
		<input name="hidden" type="hidden" />
		<input name="top" type="hidden" value="${top}"/>
		<input name="bottom" type="hidden" value="${bottom}"/>
			<table>
				<tr>
					<th>
						<input type="button" name="back" value="back" class="button_threads dark1" onclick="{document.nxt.hidden.value='back';document.nxt.submit();}"/>
					</th>
					<th>
						<input type="button"  name="next" value="next" class="button_threads dark1" onclick="{document.nxt.hidden.value='next';document.nxt.submit();}"/>
					</th>
					<th>
					Items ${range}
					</th>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>