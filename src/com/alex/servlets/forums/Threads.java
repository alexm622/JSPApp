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
		
		//set the range in threads
		request.setAttribute("range", (new String(bottom + "-" + top)));
		
		//regenerates a page if nothing has happened
		if(a == Actions.NONE) {
			//debug
			System.out.println("no action taken");
			
			System.out.println("top: " + top);
			System.out.println("botton: " + bottom );
			
			try {
				//get the output
				content = ThreadingUtils.parseToDivs(bottom);
				
				//debug
				System.out.println("content: " + content);
			} catch (ClassNotFoundException | SQLException | IdNotExists e) {
				//if for some reason there was nothing
				content = "fetch failed";
			}
			
			
			//add cookies
			response.addCookie(Cookies.makeCookie("top", (new Integer(top)).toString(), "top", 100));
			response.addCookie(Cookies.makeCookie("bottom", (new Integer(bottom)).toString(), "bottom", 100));
			
			//output the content
			request.setAttribute("Threads", content);
			
			//return page
			request.getRequestDispatcher("./threads.jsp").forward(request,response);
			
			//break before everything else happens
			return;
		}
		
		//add cookies
		response.addCookie(Cookies.makeCookie("top", (new Integer(top)).toString(), "top", 100));
		response.addCookie(Cookies.makeCookie("bottom", (new Integer(bottom)).toString(), "bottom", 100));
		
		
		//debug
		System.out.println("action taken, and that action was : " + a.name());
		System.out.println("top: " + top);
		System.out.println("botton: " + bottom );
		try {
			//generate the content of the page
			content = ThreadingUtils.parseToDivs(bottom);
			
			//debug
			System.out.println("content: " + content);
			
		} catch (ClassNotFoundException | SQLException | IdNotExists e) {
			// if the fetch failed, then set it to this String
			content = "fetch failed";
		}
		
		//set the output
		request.setAttribute("Threads", content);
		
		
		//return page
		request.getRequestDispatcher("./threads.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the action
		String action = request.getParameter("hidden");
		
		//determine what action to take
		if(action.contentEquals("next")) { //if is is next then increment top and bottom by 20
			a = Actions.NEXT;
			bottom = top;
			top += 20;
		}else if (action.contentEquals("back")) {//if it is back, decrement by 20
			a = Actions.BACK;
			if(bottom < 20 || top < 40) { //check to see if the values are still high enough
				bottom = 0;
				top = 20;
			}else { //the values are too low, just reset them
				top = bottom;
				bottom -= 20;
			}
		}else { //nothing is being done
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


