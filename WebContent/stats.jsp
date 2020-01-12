<%@page import="com.alex.utils.formatting.ParseStats"%>
<html>
	<link rel="stylesheet" href="css/style.css">
	
	<head>
		<meta charset="ISO-8859-1">
		<title>Statistics</title>
	</head>
	
	<body class="container color4">
		<div class="top-container">
			<div class="top color3">
				<div class="title center">
					Statistics
				</div>
			</div>
			<div class="status color2">
		 	<%
		 		ParseStats.format(out);
			 %>
		
		
		</div>
		</div >
		<div class="center">
			<a href="about.jsp">About</a> | <a href="contents.jsp" >Table of Contents</a> | <a href="index.jsp">Home</a>
		</div>
		
	
	</body>

</html>