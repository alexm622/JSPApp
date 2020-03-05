package com.alex.forums.comments;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alex.forums.threads.BanStatus;
import com.alex.utils.Snippits;
import com.alex.utils.sql.SQLConnect;

public class CreateComment {
	
	public static void Create(String comment, long id, long thread, long post, long parent) throws ClassNotFoundException, IOException, SQLException {
		boolean canCommeant = canComment(id, thread);
		
	}
	
	private static boolean canComment(long id, long thread) throws ClassNotFoundException, IOException, SQLException {
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		String sql = "SELECT * FROM ThreadBans WHERE userID=? AND threadID=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setLong(1, id);
		stmt.setLong(2, thread);
		
		ResultSet rs = stmt.executeQuery();
		
		if(!rs.first()) {
			return true;
		}
		ArrayList<BanStatus> bsList = new ArrayList<BanStatus>();
		
		
		while(rs.next()) {
			BanStatus bs = new BanStatus();
			bs.id = rs.getLong(1);
			bs.thread = rs.getLong(2);
			bs.minutes = rs.getLong(3);
			bs.bantype = rs.getBoolean(4);
			bs.reason = rs.getString(5);
			bs.instituted = rs.getDate(6);
			bsList.add(bs);
		}
		
		boolean returnVal = false;
		
		for(BanStatus bs : bsList) {
			
			boolean stillBanned = stillBanned(bs, Snippits.getNow());
			
			if(bs.bantype) {
				return true;
			}else if(stillBanned){
				if(!returnVal) {
					returnVal = stillBanned;
				}
			}
		}
		
		con.close();
		return returnVal;
	}

	private static boolean stillBanned(BanStatus bs, Date now) {
		
		final long milisPerMin = 60000;
		
		
		
		long milDif = milisPerMin * bs.minutes;
		
		long dif = now.getTime() - bs.instituted.getTime();
		
		if(dif >= milDif) {
			deleteFromTable(bs);
			return false;
		}else {
			return true;
		}
	}

	private static void deleteFromTable(BanStatus bs) {
		
	}

}

