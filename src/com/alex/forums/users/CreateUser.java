package com.alex.forums.users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.alex.utils.exceptions.UserExists;
import com.alex.utils.security.HashingUtils;
import com.alex.utils.sql.SQLConnect;

public class CreateUser {
	
	public static boolean create(String username, String password, String displayname) throws UserExists, ClassNotFoundException, IOException, SQLException {
		
		//remove whitespace
		username = StringUtils.deleteWhitespace(username);
		
		//return false if username is taken
		if(usernameTaken(username)) { throw new UserExists("user " + username + " already exists");}
		
		
		//hash both username and password
		String passwordHash = HashingUtils.bcryptHash(password);
		String usernameHash = HashingUtils.shaHash(username);
		
		
		
		//create a user
		return createUser(usernameHash, passwordHash, username, displayname);
	}
	
	
	private static boolean createUser(String usernameHash, String passwordHash, String username, String displayname) throws ClassNotFoundException, IOException, SQLException {
		
		Connection con = SQLConnect.getCon("forums", "signup", "signupuser");
		
		String sql = "Select id from Users ORDER BY id";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//
		ResultSet rs = stmt.executeQuery();
		
		
		//jump to last row
		rs.last();
		//get last used id
		long lastID = rs.getLong(1);
		//get next id
		long nextID = lastID + 1;
		
		System.out.println(nextID);
		
		
		//line to insert data into table
		sql = "insert into Users "
				+ "(username, id, creationDate, posts, comments, likes, dislikes, usernamehash, passwordhash)"
				+ " VALUES (?, ?, CURDATE(), 0, 0, 0, 0, ?, ?);";
		//create prepared statement
		stmt = con.prepareStatement(sql);
		
		//set username
		stmt.setString(1, displayname);
		
		//set id
		stmt.setLong(2, nextID);
		
		//set username hash
		stmt.setString(3, usernameHash);
		
		//set password hash
		stmt.setString(4, passwordHash);
		
		stmt.execute();
		
		con.commit();
		
		
		
		
		return true;
	}
	
	private static boolean usernameTaken(String username) throws ClassNotFoundException, IOException, SQLException{
		
		
		//sha256 hash the username
		String hash = HashingUtils.shaHash(username);
		
		//get connection
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		//query string
		String sql = "SELECT usernamehash, passwordhash from Users where usernamehash=?";
		
		
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//replace with username hash
		stmt.setString(1, hash);
		
		
		//execute code
		ResultSet rs = stmt.executeQuery();
		
		boolean taken = rs.next();
		
		//close the stream
		con.close();
		
		//return true that user exists, and false if it does not exist
		return (taken) ? true : false;
	}

}
