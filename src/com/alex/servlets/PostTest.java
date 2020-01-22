package com.alex.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/editor_test")
public class PostTest extends HttpServlet {
	
	private String content = "There is no content yet.";
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("content", content);
		request.getRequestDispatcher("editor_test.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.content = Jsoup.clean(request.getParameter("content"), Whitelist.basic());
		
		response.sendRedirect(request.getRequestURI());
	}
}
