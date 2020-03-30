package com.alex.servlets.forums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.forums.posts.post.PostPage;
import com.alex.utils.Debug;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.web.QueryUtils;

//this is a test class

@WebServlet("/Post")
public class PostServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8130698570041395421L;
	private String content = "There is no content yet.";
	
	//use forms?
	private boolean useForms = true;
	
	//ids
	private long threadID, id;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* TODO make all of this use query strings in the url
		 * this will make it a lot easier to link to posts and comments
		 */
		
		if(request.getQueryString() == null) {
			Debug.debug("query string is null");
		}else {
			Debug.debug("query string is : " + request.getQueryString());
			
			HashMap<String, String> hm = QueryUtils.splitQuery(request.getQueryString());
			
			//try to use the query data instead of the form data
			
			try {
				
				//test to see if those keys exist
				if(!(hm.containsKey("thread") && hm.containsKey("id"))) {
					//if they don't exist throw an error
					throw new NullPointerException();
				}
				
				//set the thread ID and id
				
				try {
					threadID = Long.parseLong(hm.get("thread"));
					id = Long.parseLong(hm.get("id"));
				}catch(NumberFormatException e){
					response.sendRedirect("Threads");
					return;
				}
				
				
				
				//don't use the forms
				useForms = false;
				Debug.debug("using query");
			}catch(NullPointerException e) {
				//we're using the form data!!
				Debug.debug("not using query data");
				useForms = true;
			}
			
		}
		
		
		
		// set the content attribute
		try {
			request.setAttribute("Post", PostPage.mainPost(id, threadID));
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IdNotExists e) {
			
			e.printStackTrace();
		}
		
		//redirect
		request.getRequestDispatcher("post.jsp").forward(request,response);
		return;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//id = Long.parseLong(request.getParameter("id"));
		
		//threadID = Long.parseLong((request.getParameter("threadID") == null) ? "0" : request.getParameter("threadID"));
		
		
	
		
		//execute a GET function
		response.sendRedirect("Post");
	}
}
