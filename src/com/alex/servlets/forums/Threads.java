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
			
			Cookies.addCookie(response, "top", (new Integer(top)).toString(), "top", 100);
			Cookies.addCookie(response, "bottom", (new Integer(bottom)).toString(), "bottom", 100);
			
			try {
				content = ThreadingUtils.parseToDivs(bottom);
				
			} catch (ClassNotFoundException | SQLException | IdNotExists e) {
				// TODO Auto-generated catch block
				content = "fetch failed";
			}
			
			//redirect
			request.getRequestDispatcher("./threads.jsp").forward(request,response);
		}
		
		
		//debug
		System.out.println("action taken, and that action was : " + a.name());
		try {
			content = ThreadingUtils.parseToDivs(top);
			bottom = top;
			top += 20;
			
		} catch (ClassNotFoundException | SQLException | IdNotExists e) {
			// TODO Auto-generated catch block
			content = "fetch failed";
		}
		
		
		
		
		request.setAttribute("content", content);
		request.getRequestDispatcher("./threads.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the action
		String action = request.getParameter("hidden");
		
		//determine what action to take
		if(action.contentEquals("next")) {
			a = Actions.NEXT;
		}else if (action.contentEquals("back")) {
			a = Actions.BACK;
		}else {
			a = Actions.NONE;
		}
		
		//get the interval
		
		Enumeration<String> parameters = request.getParameterNames();
		
		
		
		while(parameters.hasMoreElements()) {
			System.out.println(parameters.nextElement());
		}
		
		String interval = request.getParameter("interval");
		System.out.println(interval);
		String[] split = interval.split("-");
		
		//get the interval max and min
		bottom = Integer.parseInt(split[0]);
		top = Integer.parseInt(split[1]);
		
		//send redirect
		response.sendRedirect("threads.jsp");
	}
	
	
	
}

