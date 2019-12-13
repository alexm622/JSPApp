<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.alex.utils.ReadFiles, java.util.ArrayList, com.alex.beans.ServerStatus, com.alex.utils.ParseToOutput" %>
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
			<div class="status-title">
				server status
			</div>
			
			
			<%
			//this will be replaced by a better method
			ParseToOutput po = new ParseToOutput();
			ArrayList<String> output = po.parse(ReadFiles.readStatus());
			for(String s : output){
				out.print(s);
			}
			%>
		</div>
		
	</body>
</html>