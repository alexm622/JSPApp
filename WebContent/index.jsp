<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.alex.utils.ReadFiles, java.util.ArrayList, com.alex.beans.ServerStatus" %>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="css/style.css">
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body class="container">
		<div class="top-container">
			<div class="top">
				<div class="title">Server Status</div>
			</div>
		</div >
		<div class="status">
			server status
			<br>
			<%
			//this will be replaced by a better method
			ArrayList<ServerStatus> ssList = ReadFiles.readStatus();
			for(ServerStatus ss : ssList){
				out.print(ss.toString() + "<br>");
			}
			
			%>
		</div>
		
	</body>
</html>