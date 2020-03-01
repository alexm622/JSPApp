<%@page import="com.alex.utils.formatting.ParseStats"%>
<html>
	<link rel="stylesheet" href="css/style.css">
	
	<head>
		<meta charset="ISO-8859-1">
		<title>Statistics</title>
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
	
	<body class="container color4">
		<div class="top-container">
			<div class="top color3">
				<div class="title center">
					Statistics
				</div>
			</div>
			<div class="status color2">
		 	<%
		 	try{
		 		ParseStats.format(out);
		 	}catch(Exception e){
		 		out.println("nothing found");
		 	}
		 		
			 %>
		
		
		</div>
		</div >
		<div class="center">
			<a href="about.jsp">About</a> | <a href="contents.jsp" >Table of Contents</a> | <a href="index.jsp">Home</a>
		</div>
		
	
	</body>

</html>