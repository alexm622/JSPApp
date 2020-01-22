<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<p>Content:</p>
		<hr/>
		<p>${content}</p>
		<hr/>
		
		<form action="PostTest" method="POST">
			<textarea name="content"></textarea>
			<br/>
			<input type="submit" value="Submit">
		</form>
		
		<script>
			CKEDITOR.replace('content');
		</script>
	</body>
</html>