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
import com.alex.utils.Debug;
import com.alex.utils.exceptions.UserExists;
import com.alex.utils.security.Passwords;
import com.alex.utils.security.VerifyRecaptcha;

@WebServlet("/Signup")
public class SignUp extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1103004660235406995L;
	private String password1, password2;
	private String username;
	private String displayName;
	private String error = "";
	private String result = null;
       
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(error == null) { //test for no errors
			//output the success text
			request.setAttribute("success", result);
		}else if(result == null && error == null) { //if result and error are both null
			//set the error message
			request.setAttribute("errors", "both result and error are null");
		}else { 
			//output the error
			request.setAttribute("errors", error);
		}
		
		//redirect to signup page
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
		Debug.debug("password1: " + password1);
		Debug.debug("password2: " + password2);
		
		//get the login username
		this.username = Jsoup.clean(request.getParameter("username"), Whitelist.none());
		
		//test for whitespaces
		if(username.contains(" ")) {
			error = "username cannot contain whitespaces";
		}
		
		
		
		
		try {
			//check password length
			if(password1.length() <= 7) {
				error = "password is too small";
			}else if(Passwords.calculatePasswordStrength(password1) < 7) { //check complexity
				Debug.debug("password score = " + Passwords.calculatePasswordStrength(password1));
				error = "password must contain at one number, one uppercase letter, and one lowercase letter";
			}else if(!password1.equals(password2)) {// check to see if they match
				error = "passwords do not match";
				
			}
			
			//recaptcha stuff
			String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
//			Debug.debug(gRecaptchaResponse);
			//verify to check if recaptcha was good
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			
			//set the user create var to false
			boolean created = false;
			if(verify && error == "") { //if recaptcha is good and error is nothing
				Debug.debug("creating user");
				created = CreateUser.create(username, password1, displayName);
			}else {
				if(!verify) { //if recaptcha is failed
					error = "recaptcha failed";
				}
			}
			if(created) { //if the account was created
				error = null;
				//set the result
				result = "account created properly";
				Debug.debug("account " + username + " was created? " + created);
			}
			//print if the account was created
			
		} catch (UserExists e) {
			//if that username is already in use
			Debug.debug("username " + username + " already exists");
			Debug.debug(e.getMessage());
			//set the error to the error message
			error = e.getMessage();
		//errors thrown by called functions
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			if(error == null) {
				error = "Classnotfound";
			}else {
				error += " and a classnotfound exception occored";
			}
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(error == null) {
				error = "sql exception occurred";
			}else {
				error += " and an sql exception occured";
			}
		}
		
		response.sendRedirect(request.getRequestURI());
	}
}
