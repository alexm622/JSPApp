package com.alex.servlets.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.utils.Debug;
import com.alex.utils.security.Verification;
import com.alex.utils.web.Cookies;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5688587608727398561L;
	private String password;
	private String username;
	private boolean correct = false;
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set the result
		
		
		request.setAttribute("result", correct);
		
		
		//forward to login page
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//grab the password
		this.password = Jsoup.clean(request.getParameter("password"), Whitelist.basic());
		
		//grab the username
		this.username = StringUtils.deleteWhitespace(Jsoup.clean(request.getParameter("username"), Whitelist.basic()));
		
		//try to verify
		try {
			//check the username and password against the database
			correct = Verification.isCorrect(username, password);
			Debug.debug(correct);
			
			if(correct) {
				//TODO add cookies
				Cookies.addTokenCookie(response, request, username);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//send a redirect
		response.sendRedirect(request.getRequestURI());
	}
}
