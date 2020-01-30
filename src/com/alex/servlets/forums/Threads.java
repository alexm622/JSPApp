package com.alex.servlets.forums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.forums.threads.ThreadingUtils;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.web.Cookies;

@WebServlet("/Threads")
public class Threads extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2427882201717517381L;
	
	
	private String content = "There is no content yet.";
	private int top = 20, bottom = 0;
	private Actions a = Actions.NONE;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//return the same exact page if nothing is happening
		if(a == Actions.NONE) {
			//debug
			
			
			System.out.println("no action taken");
			
			System.out.println("top: " + top);
			System.out.println("botton: " + bottom );
			
			
			
			try {
				content = ThreadingUtils.parseToDivs(bottom);
				System.out.println("content: " + content);
				
			} catch (ClassNotFoundException | SQLException | IdNotExists e) {
				// TODO Auto-generated catch block
				content = "fetch failed";
			}
			
			
			
			response.addCookie(Cookies.makeCookie("top", (new Integer(top)).toString(), "top", 100));
			response.addCookie(Cookies.makeCookie("bottom", (new Integer(bottom)).toString(), "bottom", 100));
			
			request.setAttribute("Threads", content);
			
			//redirect
			request.getRequestDispatcher("./threads.jsp").forward(request,response);
			return;
		}
		
		response.addCookie(Cookies.makeCookie("top", (new Integer(top)).toString(), "top", 100));
		response.addCookie(Cookies.makeCookie("bottom", (new Integer(bottom)).toString(), "bottom", 100));
		
		
		//debug
		System.out.println("action taken, and that action was : " + a.name());
		System.out.println("top: " + top);
		System.out.println("botton: " + bottom );
		try {
			
			content = ThreadingUtils.parseToDivs(bottom);
			System.out.println("content: " + content);
			
		} catch (ClassNotFoundException | SQLException | IdNotExists e) {
			// TODO Auto-generated catch block
			content = "fetch failed";
		}
		
		
		
		
		request.setAttribute("Threads", content);
		request.getRequestDispatcher("./threads.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the action
		String action = request.getParameter("hidden");
		
		//determine what action to take
		if(action.contentEquals("next")) {
			a = Actions.NEXT;
			bottom = top;
			top += 20;
		}else if (action.contentEquals("back")) {
			a = Actions.BACK;
			if(bottom < 20 || top < 40) {
				bottom = 0;
				top = 20;
			}else {
				top = bottom;
				bottom -= 20;
			}
		}else {
			a = Actions.NONE;
			
		}
		
		//get the interval
		
		Enumeration<String> parameters = request.getParameterNames();
		
		
		
		
		
		
		while(parameters.hasMoreElements()) {
			System.out.println(parameters.nextElement());
		}
		
		//send redirect
		response.sendRedirect("Threads");
	}
	
	
	
}


