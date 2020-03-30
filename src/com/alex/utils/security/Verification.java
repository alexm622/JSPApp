package com.alex.utils.security;

import java.sql.Statement;

import com.alex.utils.Debug;
import com.alex.utils.sql.SQLConnect;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {
	
	
	public static boolean isCorrect(String username, String password) throws Exception{
		
		//get the connection to the sql server
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		//hash the username
		username = Hash(username);
		
		//check to see if the username and password exist in the database
		return exists(username, password, con); 
	}
	
	private static boolean exists(String usernameHash, String password, Connection con) throws SQLException {
		
		//sql query
		String sql = "SELECT usernamehash, passwordhash from Users where usernamehash=?";
		
		
		//get the prepared statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the where to equal the username
		stmt.setString(1, usernameHash);
		
		//get the results for the query
		ResultSet rs = stmt.executeQuery();
		
		//create the temp password hash string
		String hash = null;
		
		//iterate through the results
		while(rs.next()) {
			//get the password hash
			hash = rs.getString(2);
			
			//print the hash
			Debug.debug(hash);
			
			//test to see if the  hash and password correspond
			//if the hash and password correspond break and return true
			if(HashingUtils.findBcryptMatch(password, hash)) {return true;}
		}
		
		//close the connection
		con.close();
		
		//return false because there is no match
		return false;

	}
	
	private static String Hash(String username) throws NoSuchAlgorithmException{
		//sha256 hash the username
		String hash = HashingUtils.shaHash(username);
		
		//print the hash
		Debug.debug(hash);
		
		//return the hash
		return hash;
	}
}
