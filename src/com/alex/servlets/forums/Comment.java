package com.alex.servlets.forums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.utils.sql.SQLConnect;
import com.alex.utils.web.QueryUtils;
import com.alex.utils.web.SessionUtils;

@WebServlet("/Comment")
public class Comment extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4962336299528017400L;
	private static String content;
	
	private long thread, parentComment, post;
	private long id;
	
	private SessionUtils su;
	
	private boolean successful;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the query data
		String query = request.getQueryString();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = QueryUtils.splitQuery(query);
		if(hm.equals(new HashMap<String, String>()) || !hasAll(hm)) {
			System.out.println("Comment query failed");
			request.getRequestDispatcher("Threads").forward(request,response);
			return;		
		}
		
		try {
			thread = Long.parseLong(hm.get("thread"));
			parentComment = Long.parseLong(hm.get("id"));
			post = Long.parseLong(hm.get("post"));
		}catch(NumberFormatException e) {
			System.out.println("Comment query failed");
			request.getRequestDispatcher("Threads").forward(request,response);
			return;	
		}
		
		// set the content attribute
		request.setAttribute("content", content);
		
		//redirect
		request.getRequestDispatcher("Comment").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the content in the textarea
		content = Jsoup.clean(request.getParameter("content"), Whitelist.basic());
		
		//get the user id
		
		try {
			String token = su.getToken();
			id = SessionUtils.getId(token);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO when the getting token fails
			return;
		}
		
		
		//execute a GET function
		response.sendRedirect(request.getRequestURI());
	}
	
	private boolean hasAll(HashMap<String, String> hm) {
		if(hm.containsKey("post") &&
		   hm.containsKey("thread") &&
		   hm.containsKey("id")) {
			return true;
		}
		return false;
	}
}
