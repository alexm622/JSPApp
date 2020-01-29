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

import com.alex.utils.exceptions.InvalidUsername;
import com.alex.utils.security.HashingUtils;
import com.alex.utils.sql.SQLConnect;

public class Cookies {
	
	public static void addTokenCookie(HttpServletResponse response, HttpServletRequest request, String username) throws ClassNotFoundException, IOException, SQLException, InvalidUsername {
		
		
		String unameHash = HashingUtils.shaHash(username);
		
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		long id = getID(unameHash, con);
		
		if(id==-1) {
			throw new InvalidUsername(username);
		}
		
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
	
	
	
	
	private static long getID(String unameHash, Connection con) throws SQLException {
		
		String sql = "SELECT id FROM Users WHERE usernameHash=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, unameHash);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			return  rs.getLong(1);
		}else {
			return -1;	
		}
	}
	
	public static void addCookie(HttpServletResponse response,
			String name, String value, String comment, int expire) {
		Cookie c = new Cookie(name, value);
		
		c.setComment(comment);
		
		c.setMaxAge(expire);
		
		response.addCookie(c);
		
	}
	
	public static ArrayList<Cookie> getCookies(HttpServletRequest request, String name) {
		ArrayList<Cookie> cook = new ArrayList<Cookie>();
		
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c : cookies) {
			if(c.getName().contains(name)) {
				cook.add(c);
			}
		}
		
		
		return cook;
	}
	
	
	//returns true if a token was invalidated
	private static boolean invalidateTokens(HttpServletRequest request, String unameHash, Connection con) throws SQLException {
		//make the arraylist for the group of cookies
		ArrayList<Cookie> mydomain = new ArrayList<Cookie>();
		
		//get the client's current cookies
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c : cookies) {
			if(c.getDomain() == "alexcomeau.com" || c.getDomain() == "localhost"
					|| c.getDomain() == "10.0.0.4") {
				//only store my own cookies
				mydomain.add(c);
			}
		}
		boolean exists = false;
		for(Cookie c : cookies) {
			if(c.getName().contains("Token")) {
				String token = c.getValue();
				//set the query
				String query = "SELECT * FROM ActiveTokens WHERE TokenID=?";
				
				//create the prepared statement
				PreparedStatement stmt = con.prepareStatement(query);
				
				//set the first term
				stmt.setString(1, token);
				
				//get the results
				ResultSet rs = stmt.executeQuery();
				
				//there is a cookie field for the token
				boolean remove = rs.next();
				//debug
				System.out.println("does this token already exist: " + remove);
				
				
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
			if(c.getDomain() == "alexcomeau.com" || c.getDomain() == "localhost"
					|| c.getDomain() == "10.0.0.4") {
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
			
			
			//get the random characters
			Long rand = random.nextLong();
			
			
			
			
			//create hash
			tokenHash = HashingUtils.shaHash((rand.toString()));
			
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
			
			
			
		} while (goodToken);
		
		System.out.println("generated token of " + tokenHash);
		
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
