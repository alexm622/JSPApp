<%@page import="com.alex.constants.Games"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.alex.utils.ReadFiles, java.util.ArrayList, com.alex.beans.ServerStatus, com.alex.utils.ParseToOutput" %>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="css/style.css">
	<head>
		<meta charset="ISO-8859-1">
		<title>Server Status Homepage</title>
	</head>
	<body class="container color4">
		<div class="top-container">
			<div class="top color3">
				<div class="title center">Server Status</div>
			</div>
		</div >
		<div class="status color2">
			<div class="status-title color3 center">
				Servers
			</div>
			<br>
			
			
			<%
			//this will be replaced by a better method
			
			ParseToOutput po = new ParseToOutput();
			po.parse(ReadFiles.readStatus(), out);
			
			%>
			
		</div>
		<div class="center">
			<a href="about.jsp">About</a> | <a href="contents.jsp" >Table of Contents</a>
		</div>
	</body>
</html>