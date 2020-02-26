package com.alex.utils.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alex.forums.users.utils.UserUtils;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.sql.SQLConnect;

public class SessionUtils {

	private HttpServletRequest request;
	
	public SessionUtils(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getDisplayName() throws ClassNotFoundException, IOException, SQLException {
		
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		String token = getToken();
		
		//debug variable
		System.out.println("the token is " + token);
		
		if(token == "none") {
			
			//debug output
			System.out.println("closing, no token found");
			
			//close the connection
			con.close();
			
			//return none
			return "none";
		}
		
		//convert the token to an id
		long id = tokenToID(token, con);
		
		//debug sysout
		System.out.println("the id is " + id);
		
		if(id == -1) {
			return "";
		}
		
		try {
			return UserUtils.idToDisplayName(id, con);
		} catch (IdNotExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static long tokenToID(String token, Connection con) throws SQLException {
		
		//get the sql query
		String sql = "SELECT userid FROM ActiveTokens WHERE TokenID=?";
		
		//insert the token into the sql statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the parameter
		stmt.setString(1, token);
		
		//execute and get results
		ResultSet rs = stmt.executeQuery();
		
		//if results came back, then do something
		if(rs.first()) {
			return rs.getLong(1);
		}else { //if there are no results return -1
			return -1;
		}
	}
	
	private String getToken() {
		//get the cookies
		Cookie[] cookies = request.getCookies();
		
		//debug output
		if(cookies == null) {
			return "none";
		}
		System.out.println("cookie count: " + cookies.length);
		
		//iterate through the cookies
		int temp = 0;
		for(Cookie c : cookies) {
			//cookie number
			temp++;
			
			//debug output
			System.out.println("Cookie number " + temp + " has the name " + c.getName());
			//if the cookie is a token cookie return its value
			if(c.getName().contains("Token")) {
				return c.getValue();
			}
		}
		
		//no cookie found, return nothing
		return "none";
		
		
	}
	
	
}
