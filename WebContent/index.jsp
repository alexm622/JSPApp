<%@page import="com.alex.utils.sql.ReadStatuses"%>
<%@page import="com.alex.constants.Games"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.alex.utils.ReadFiles,java.util.ArrayList,com.alex.beans.ServerStatus,com.alex.utils.formatting.ParseToOutput" %>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="./css/style.css" type="text/css">
	<head>
		<meta charset="ISO-8859-1">
		<title>Server Status Homepage</title>
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
				po.parse(ReadStatuses.read(), out);
			%>
		</div>
		<div class="center">
			<a href="about.jsp">About</a> | <a href="contents.jsp" >Table of Contents</a> | <a href="stats.jsp">Statistics</a> | <a href="login.jsp"> Login Page</a>
			| <a href="signup.jsp">Sign Up</a>
		</div>
	</body>
</html>