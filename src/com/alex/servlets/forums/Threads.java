package com.alex.servlets.forums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/Threads")
public class Threads extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2427882201717517381L;
	
	
	private String content = "There is no content yet.";
	private int top, bottom;
	private Actions a;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(a == Actions.NONE) {
			request.getRequestDispatcher("editor_test.jsp").forward(request,response);
		}
		
		
		request.setAttribute("content", content);
		request.getRequestDispatcher("threads.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("hidden");
		
		if(action.contentEquals("next")) {
			a = Actions.NEXT;
		}else if (action.contentEquals("back")) {
			a = Actions.BACK;
		}else {
			a = Actions.NONE;
		}
		
		String interval = request.getParameter("interval");
		String[] split = interval.split("-");
		
		bottom = Integer.parseInt(split[0]);
		top = Integer.parseInt(split[1]);
		
		
		
		response.sendRedirect("/threads.jsp");
	}
	
}


