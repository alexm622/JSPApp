package com.alex.servlets.forums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.utils.web.SessionUtils;

@WebServlet("/Comment")
public class Comment extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4962336299528017400L;
	private static String content;
	
	private long thread, parentComment, post;
	private long userToken;
	
	private SessionUtils su;
	
	private boolean successful;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set the content attribute
		request.setAttribute("content", content);
		
		//redirect
		request.getRequestDispatcher("forums/editor_test.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the content in the textarea
		content = Jsoup.clean(request.getParameter("content"), Whitelist.basic());
		
		//execute a GET function
		response.sendRedirect(request.getRequestURI());
	}
}
