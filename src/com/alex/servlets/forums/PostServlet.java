package com.alex.servlets.forums;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.forums.posts.post.PostPage;
import com.alex.utils.exceptions.IdNotExists;

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
		try {
			request.setAttribute("Post", PostPage.mainPost(id, threadID));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IdNotExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//redirect
		request.getRequestDispatcher("post.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		id = Long.parseLong(request.getParameter("id"));
		
		threadID = Long.parseLong((request.getParameter("threadID") == null) ? "0" : request.getParameter("threadID"));
		
		
	
		
		//execute a GET function
		response.sendRedirect("Post");
	}
}
