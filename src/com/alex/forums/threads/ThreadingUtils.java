package com.alex.forums.threads;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alex.utils.sql.SQLConnect;

public class ThreadingUtils {
	
	public static ArrayList<Thread> getThreads(int offset) throws ClassNotFoundException, IOException, SQLException{
		ArrayList<Thread> threads = new ArrayList<>();
		
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		String sql = "SELECT * FROM forums.Threads LIMIT 20 OFFSET ?";
		
		
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, offset);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Thread t = new Thread(
					rs.getLong(1), //get id
					rs.getString(2), //get name
					rs.getString(3), //get creator
					rs.getLong(4), //get creatorID
					rs.getDate(5), //get creationDate
					rs.getLong(6), //get postcount
					rs.getBoolean(7), //get isDeleted
					rs.getBoolean(8), //get isArchived
					rs.getBoolean(9)); //get isLocked
			
			//add the thread
			threads.add(t);
			
		}
		return threads;
	}
	
	public String parseToDivs(int offset) {
		
		String div = " <div class=\"status-entry color6\"> ? </div>";
		
		
		
		
		
		return null;
	}
	
}
