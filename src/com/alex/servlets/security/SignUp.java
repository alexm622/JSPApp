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

import com.alex.forums.users.CreateUser;

@WebServlet("/Login")
public class SignUp extends HttpServlet {
	
	private String password1, password2;
	private String username;
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.password1 = Jsoup.clean(request.getParameter("password1"), Whitelist.basic());
		this.password2 = Jsoup.clean(request.getParameter("password2"), Whitelist.basic());	
		
		this.username = StringUtils.deleteWhitespace(Jsoup.clean(request.getParameter("username"), Whitelist.basic()));
		
		
		
		try {
			if(password1 != password2) {
				throw new Error("password mismatch"); 
			}
			boolean created = CreateUser.create(username, password1);
			System.out.println(created);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getRequestURI());
	}
}
