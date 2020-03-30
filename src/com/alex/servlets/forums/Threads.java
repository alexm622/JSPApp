package com.alex.servlets.forums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.forums.threads.ThreadingUtils;
import com.alex.utils.Debug;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.web.Cookies;
import com.alex.utils.web.QueryUtils;

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
		
		Debug.debug("query string is : " + request.getQueryString());
		
		HashMap<String, String> hm = QueryUtils.splitQuery(request.getQueryString());
		
		try { //get top and bottom
			if(!(hm.containsKey("top")) || !(hm.containsKey("bottom"))) {
				throw new NullPointerException();
			}
			
			int tempTop = 20, tempBottom = 0;
			
			try {
				tempTop = Integer.parseInt(hm.get("top"));
				tempBottom = Integer.parseInt(hm.get("bottom"));
				
			}catch(NumberFormatException e) {
				
				response.sendRedirect("Threads");
				return;
			}
			if((tempTop > 0) && (tempBottom >= 0) && (tempTop > tempBottom) && ((tempTop - tempBottom) == 20)) { // verify that these values are good
				top = tempTop;
				bottom = tempBottom;
			}else {
				//these values didn't match anything
				throw new NullPointerException();
			}
			Debug.debug("using query for top and bottom");
			
		}catch(NullPointerException e){
			Debug.debug("not using query for top and bottom");
		}
		
		//set the range in threads
		request.setAttribute("range", (new String(bottom + "-" + top)));
		
		//regenerates a page if nothing has happened
		if(a == Actions.NONE) {
			//debug
			Debug.debug("no action taken");
			
			Debug.debug("top: " + top);
			Debug.debug("botton: " + bottom );
			
			try {
				//get the output
				content = ThreadingUtils.parseToDivs(bottom);
				
				//debug
				Debug.debug("content: " + content);
			} catch (ClassNotFoundException | SQLException | IdNotExists e) {
				//if for some reason there was nothing
				content = "fetch failed";
			}
			
			
			String next, back;
			
			int Nexttop, Nextbottom;
			
			if(bottom < 20 || top < 40) { //check to see if the values are still high enough
				Nextbottom = 0;
				Nexttop = 20;
			}else { //the values are too low, just reset them
				Nexttop = bottom;
				Nextbottom = bottom -  20;
			}
			
			final String redirect = "\"window.location.href = 'Threads?top=!&bottom=~'\"";
			next = redirect.replace("!", (new Integer(top + 20).toString()))
					   .replace("~", (new Integer(bottom + 20).toString()));
		
			back = redirect.replace("!", (new Integer(Nexttop).toString()))
						   .replace("~", (new Integer(Nextbottom).toString()));
		
			
			request.setAttribute("next", next);
			request.setAttribute("back", back);
			
			//output the content
			request.setAttribute("Threads", content);
			
			//return page
			request.getRequestDispatcher("./threads.jsp").forward(request,response);
			
			//break before everything else happens
			return;
		}
		
		
		
		
		//debug
		Debug.debug("action taken, and that action was : " + a.name());
		Debug.debug("top: " + top);
		Debug.debug("botton: " + bottom );
		try {
			//generate the content of the page
			content = ThreadingUtils.parseToDivs(bottom);
			
			if(!content.contains("div")) {
				content = " <div class=\"status-entry color6\"> nothing to see here </div>";
			}
			
			//debug
			Debug.debug("content: " + content);
			
		} catch (ClassNotFoundException | SQLException | IdNotExists e) {
			// if the fetch failed, then set it to this String
			content = "fetch failed";
		}
		
		String next, back;
		
		int Nexttop, Nextbottom;
		
		if(bottom < 20 || top < 40) { //check to see if the values are still high enough
			Nextbottom = 0;
			Nexttop = 20;
		}else { //the values are too low, just reset them
			Nexttop = bottom;
			Nextbottom = bottom -  20;
		}
		
		final String redirect = "\"window.location.href = 'Threads?top=!&bottom=~'\"";
		next = redirect.replace("!", (new Integer(top + 20).toString()))
				   .replace("~", (new Integer(bottom + 20).toString()));
	
		back = redirect.replace("!", (new Integer(Nexttop - 20).toString()))
					   .replace("~", (new Integer(Nextbottom - 20).toString()));
	
		
		request.setAttribute("next", next);
		request.setAttribute("back", back);
		
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
			Debug.debug(parameters.nextElement());
		}
		
		//send redirect
		response.sendRedirect("Threads");
	}
	
	
	
}


