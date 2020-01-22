package com.alex.servlets.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.utils.security.Verification;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private String password;
	private String username;
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.password = Jsoup.clean(request.getParameter("password"), Whitelist.basic());
		
		this.username = Jsoup.clean(request.getParameter("username"), Whitelist.basic());
		
		try {
			boolean correct = Verification.isCorrect(username, password);
			System.out.println(correct);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getRequestURI());
	}
}
