package com.alex.servlets.forums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

//this is a test class

@WebServlet("/Post")
public class PostServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8130698570041395421L;
	private String content = "There is no content yet.";
	
	private long threadID, id;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set the content attribute
		request.setAttribute("content", content);
		
		//redirect
		request.getRequestDispatcher("post.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		
		//execute a GET function
		response.sendRedirect("post");
	}
}
