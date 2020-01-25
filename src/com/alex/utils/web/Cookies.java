package com.alex.utils.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.utils.security.HashingUtils;
import com.alex.utils.sql.SQLConnect;

public class Cookies {

	public static void addCookie(HttpServletResponse response, HttpServletRequest request, String username) throws ClassNotFoundException, IOException, SQLException {
		
		
		String unameHash = HashingUtils.shaHash(username);
		
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		long id = getID(unameHash, con);
		
		deleteCookies(request);
		invalidateTokens(request, unameHash, con);
		
		//get the cookie to add
		Cookie cookie = createCookie(id, con);
		
		String token = cookie.getValue();
		System.out.println("TokenID " + token);
		
		addToken(token, id, con);
		//add the cookie
		response.addCookie(cookie);
		con.close();
	}
	
	private static long getID(String unameHash, Connection con) {
		
		String sql = "SELECT id FROM Users WHERE usernameHash=?";
		
		
		
		
		return 0;
	}
	
	//returns true if a token was invalidated
	private static boolean invalidateTokens(HttpServletRequest request, String unameHash, Connection con) throws SQLException {
		//make the arraylist for the group of cookies
		ArrayList<Cookie> mydomain = new ArrayList<Cookie>();
		
		//get the client's current cookies
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c : cookies) {
			if(c.getDomain() == "alexcomeau.com") {
				//only store my own cookies
				mydomain.add(c);
			}
		}
		boolean exists = false;
		for(Cookie c : mydomain) {
			if(c.getComment() == "token") {
				String token = c.getValue();
				//set the query
				String query = "SELECT * FROM ActiveTokens WHERE TokenID=?";
				
				//create the prepared statement
				PreparedStatement stmt = con.prepareStatement(query);
				
				//set the first term
				stmt.setString(1, token);
				
				//get the results
				ResultSet rs = stmt.executeQuery();
				
				
				boolean remove = rs.next();
				
				//there is a cookie field for the token
				exists = !exists;
				
				if(remove) {
					String sql = "DELETE FROM ActiveTokens WHERE TokenID=?";
					stmt = con.prepareStatement(sql);
					
					//set the tokenID to to the token
					stmt.setString(1, token);
					
					//delete the token
					stmt.execute();
				}
				
								
				
			}
		}
		return exists;
	}
	
	private static void deleteCookies(HttpServletRequest request) {
		
		//make the arraylist for the group of cookies
		ArrayList<Cookie> mydomain = new ArrayList<Cookie>();
		
		//get the client's current cookies
		Cookie[] cookies = request.getCookies();
		
		//iterate through the cookeis
		for(Cookie c : cookies) {
			if(c.getDomain() == "alexcomeau.com") {
				//only store my own cookies
				mydomain.add(c);
			}
		}
		
		//expire all the cookies for this website
		for(Cookie c : mydomain) {
			c.setMaxAge(0);
		}
		
	}
	
	private static Cookie createCookie(long id, Connection con) throws SQLException {
		
		//initialize the token
		String token = createToken(con);
		
		//make a new token
		Cookie c = new Cookie("Token", token);
		
		//set the max age to one week
		c.setMaxAge(604800);
		
		//return the cookie
		return c;
	}
	
	
	public static String createToken(Connection con) throws SQLException {
		boolean goodToken = false;
		String tokenHash;
		do {
			//create the variables
			SecureRandom random = new SecureRandom();
			byte[] bytes = new byte[20];
			
			//get the random characters
			random.nextBytes(bytes);
			
			//make the bytebuffer
			ByteBuffer bb = ByteBuffer.wrap(bytes);
			
			//export to a string
			String tokenString = bb.toString();
			
			//create hash
			tokenHash = HashingUtils.shaHash(tokenString);
			
			//set the query
			String query = "SELECT * FROM ActiveTokens WHERE TokenID=?";
			
			//create the prepared statement
			PreparedStatement stmt = con.prepareStatement(query);
			
			//set the first term
			stmt.setString(1, tokenHash);
			
			//get the results
			ResultSet rs = stmt.executeQuery();
			
			//if that token already exists make a new token
			if(!rs.next()) {
				goodToken = !goodToken;
			}
			
			System.out.println("generated token of " + tokenHash);
			
		} while (goodToken);
		
		
		//return the generated token
		return tokenHash;
	}
	
	private static void addToken(String token, long id, Connection con) throws SQLException {
		
		//set the statement
		String sql = "INSERT INTO ActiveTokens (userid, creationdate, tokenID) VALUES (?, CURDATE(), ?);";
		
		//create the prepared statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the first parameter
		stmt.setLong(1, id);
		
		//set the second parameter
		stmt.setString(2, token);
		
		//execute statement
		stmt.execute();
	}
	
}
