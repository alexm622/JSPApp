package com.alex.utils.security;

import java.sql.Statement;

import com.alex.utils.sql.SQLConnect;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {
	
	
	public static boolean isCorrect(String username, String password) throws Exception{
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		
		
		 username = Hash(username);
		
		
		
		
		return exists(username, password, con); 
	}
	
	private static boolean exists(String usernameHash, String password, Connection con) throws SQLException {
		
		String sql = "SELECT usernamehash, passwordhash from Users where usernamehash=?";
		
		
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, usernameHash);
		
		ResultSet rs = stmt.executeQuery();
		String hash = null;
		while(rs.next()) {
			 hash = rs.getString(2);
			System.out.println(hash);
			if(HashingUtils.findBcryptMatch(password, hash)) {return true;}
		}
		
		con.close();
		
		return false;

	}
	
	private static String Hash(String username) throws NoSuchAlgorithmException{
		String hash = HashingUtils.shaHash(username);
		 
		
		System.out.println(hash);
		
		
		return hash;
	}
}
