package com.alex.forums.threads;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alex.forums.users.utils.UserUtils;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.sql.SQLConnect;

public class ThreadingUtils {
	
	private static ArrayList<Thread> getThreads(int offset, Connection con) throws ClassNotFoundException, IOException, SQLException{
		
		//arraylist of threads
		ArrayList<Thread> threads = new ArrayList<>();
		
		//fetch 20 items from table with offset offset
		String sql = "SELECT * FROM forums.Threads LIMIT 20 OFFSET ?";
		
		//get the prepare statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the parameters
		stmt.setInt(1, offset);
		
		//execute the query
		ResultSet rs = stmt.executeQuery();
		
		//iterate through the rows
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
	
	public static String parseToDivs(int offset) throws ClassNotFoundException, IOException, SQLException, IdNotExists {
		
		//html elements
		final String div = " <div class=\"status-entry color6\"> <table> ? </table> </div>";
		final String row = "<tr> ? </tr>";
		final String column = "<th> ? <th>";
		
		//stringbuilder for final output
		StringBuilder sb = new StringBuilder();
		
		//get connection
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		//arraylist of threads
		ArrayList<Thread> threads = getThreads(offset, con);
		
		for(Thread t : threads) {
			String out = div.replace("?", row);
			
			//make an arraylist containing all of the columns
			ArrayList<String> columns = new ArrayList<String>();
			
			//add columns
			columns.add(column.replace("?", "Name: " + t.name));
			columns.add(column.replace("?", "Creator: " + UserUtils.idToDisplayName(t.id, con)));
			columns.add(column.replace("?", "Posts: " + t.postcount));
			
			//make the stringbuilder to combine all the columns
			StringBuilder columnConcat = new StringBuilder();
			
			//iterate throught the columns and add them to the stringbuilder
			for(String s : columns) {
				columnConcat.append(s);
			}
			
			//append to the stringbuilder
			sb.append(columnConcat.toString());
			
		}
		
		//return html elements
		return sb.toString();
	}
	
}
