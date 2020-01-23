package com.alex.servlets.security;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.alex.forums.users.CreateUser;
import com.alex.utils.exceptions.UserExists;
import com.alex.utils.security.Passwords;
import com.alex.utils.security.VerifyRecaptcha;

@WebServlet("/Signup")
public class SignUp extends HttpServlet {
	
	private String password1, password2;
	private String username;
	private String displayName;
	private String error = "";
	private String result = null;
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(error == null) {
			request.setAttribute("success", result);
		}else if(result == null && error == null) {
			request.setAttribute("errors", "both result and error are null");
		}else {
			request.setAttribute("errors", error);
		}
		
		request.getRequestDispatcher("signup.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the passwords
		this.password1 = Jsoup.clean(request.getParameter("password1"), Whitelist.none());
		this.password2 = Jsoup.clean(request.getParameter("password2"), Whitelist.none());	
		
		//get the display name
		this.displayName = Jsoup.clean(request.getParameter("display"), Whitelist.none());
		
		//print the passwords
		System.out.println("password1: " + password1);
		System.out.println("password2: " + password2);
		
		//get the login username
		this.username = Jsoup.clean(request.getParameter("username"), Whitelist.none());
		
		//test for whitespaces
		if(username.contains(" ")) {
			error = "username cannot contain whitespaces";
		}
		
		
		
		
		try {
			//check password length
			if(password1.length() <= 7) {
				error += "password is too small";
			}else if(Passwords.calculatePasswordStrength(password1) < 7) { //check complexity
				error = "password must contain at one number, one uppercase letter, and one lowercase letter";
			}else if(!password1.equals(password2)) {
				error = "passwords do not match";
				
			}
			String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			System.out.println(gRecaptchaResponse);
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			boolean created = false;
			if(verify && error == "") {
				System.out.println("creating user");
				created = CreateUser.create(username, password1, displayName);
			}else {
				if(!verify) {
					error = "recaptcha failed";
				}
			}
			if(created) {
				error = null;
				result = "account created properly";
			}
			System.out.println(created);
		} catch (UserExists e) {
			// TODO Auto-generated catch block
			System.out.println("caught error");
			System.out.println(e.getMessage());
			error = e.getMessage();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getRequestURI());
	}
}
