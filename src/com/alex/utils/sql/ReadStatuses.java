package com.alex.utils.sql;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

import com.alex.beans.ServerStatus;
import com.alex.utils.Snippits;

public class ReadStatuses {
	
	
	
	public static ArrayList<ServerStatus> read() throws Exception{
		
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = SQLConnect.getCon("gameserver", "server", "serverpass");
		
		
		
		//read the servervars server
		String sql = "SELECT * FROM servervars";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		
		
		
		return toBeans(rs, con);
	}
	
	//convert the resultset to the java beans
	private static ArrayList<ServerStatus> toBeans(ResultSet rs, Connection con) throws Exception{
		System.out.println("reading from sql"); 
		ArrayList<ServerStatus> ssl = new ArrayList<ServerStatus>();
		while(rs.next()) {
			ServerStatus ss = new ServerStatus();
			ss.setName(rs.getString(1));
			ss.setMap(rs.getString(2));
			ss.setDescription(rs.getString(3));
			ss.setTags(rs.getString(4));
			ss.setOperatingSystem(rs.getString(5).charAt(0));
			ss.setNumOfPlayers(rs.getInt(6));
			ss.setMaxPlayers(rs.getInt(7));
			ss.setNumOfBots(rs.getInt(8));
			ss.setAppID(rs.getLong(9));
			ss.setServerID(rs.getLong(10));
			ss.setDedicated(rs.getBoolean(11));
			ss.setSecure(rs.getBoolean(12));
			
			
			ssl.add(ss);
		}
		
		return ssl;
	}
	
	
}
