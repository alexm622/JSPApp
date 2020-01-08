<%@page import="com.alex.utils.Snippits"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="css/style.css">
	<head>
		<meta charset="ISO-8859-1">
		<title>About This Site</title>
	</head>
	<body class="container color4">
		<div class="top-container ">
			<div class="color3 top">
				<div class="title center">About This Website</div>
			</div>
		</div>
		<div class="top color2">
			<div class="top color3">
				<h1 style="font-size: 20pt" class="Left">What is this site?</h1>
				this site was create by Alex Comeau to display the status of steam servers which he owns			
			</div>
			<br>
			<div class="top color3">
				<h1 style="font-size: 20pt" class="Left">What was used to make this site?</h1>
				<h2 style="font-size: 15pt">
					to create the site's main webpages i used
					<br>
					<ul style="font-size: 10pt">
						<li> JSP (Java Server Pages)
						<li> Java
						<li> HTML
						<li> CSS
						<li> JavaScript
					</ul>
				</h2>
			</div>
			<br>
			<div class="top color3">
				<h1 style="font-size: 20pt" class="Left">How is this site hosted?</h1>
				<h2 style="font-size: 15pt">
					This site is hosted within a Docker Container running Apache Tomcat, the server hosting program made specifically for JSP.
					The Docker Container is running on computer that has Ubuntu Server installed
					<br>
					<br>
					The DNS service is being provided by <a href="https://noip.com/">noip.com</a> 
				</h2>
			</div>
			<br>
			<div class="top color3">
				<h1 style="font-size: 20pt" class="Left">Resources Used</h1>
				<a href="http://tomcat.apache.org/">Apache Tomcat</a>: This was the chosen webserver program for hosting the server <br>
				
				<a href="https://docker.com">Docker</a>: This is the thing that the tomcat server was running in<br>
				
				<a href="https://ubuntu.com/">Ubuntu</a>: This was the operating system that the Docker Container ran in <br>
				
				<a href="https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">Java-8</a>: This was the programming language used for all of this <br>
				
				<a href="https://www.eclipse.org/">Eclipse</a>: This is the IDE of choice for creating this <br>
				
				<a href="https://maven.apache.org/">Maven</a> : This was for keeping the libraries used in programming this project up to date <br>
				
				<a href="https://github.com/ribasco/async-gamequery-lib">Async GameQuery Library</a>: This was for getting the list of running servers<br>
				
				<a href="https://github.com">GitHub</a>: This made it so I could develop the server on two different computers and keep the changes synchronized between multiple computers <br>
				
			</div>
			<br>
			<div class="top color3">
				<h1 style="font-size: 20pt" class="Left">The GitHub repository</h1>
				Is located  <a href="https://github.com/alexm622/JSPApp">here</a>			
			</div>
			
		</div>
	</body>
</html>