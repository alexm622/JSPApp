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
	<body>
	<div>
	${Threads}
	</div>
	<div>
	<input type="hidden" name="interval" value="0-20">
		<form name="nxt" method="POST" action="Threads">	
		<input name="hidden" type="hidden" />
			<table>
				<tr>
				<th>
				<button name="back" value="back" onclick="{document.nxt.hidden.value='back';document.nxt.submit();}">Back</button>
				</th>
				<th>
				<button name="next" value="next" onclick="{document.nxt.hidden.value='next';document.nxt.submit();}">Next</button>
				</th>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>