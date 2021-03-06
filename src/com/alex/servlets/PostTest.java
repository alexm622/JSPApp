package com.alex.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

//this is a test class

@WebServlet("/editor_test")
public class PostTest extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6162488347297089549L;
	private String content = "There is no content yet.";
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set the content attribute
		request.setAttribute("content", content);
		
		//redirect
		request.getRequestDispatcher("editor_test.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the content in the textarea
		this.content = Jsoup.clean(request.getParameter("content"), Whitelist.basic());
		
		//execute a GET function
		response.sendRedirect(request.getRequestURI());
	}
}
