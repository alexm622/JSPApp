package com.alex.forums.users.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alex.utils.exceptions.IdNotExists;

public class UserUtils {
	public static String idToDisplayName(long id, Connection con) throws IdNotExists, SQLException{
		
		String sql = "SELECT username FROM Users WHERE id=?";
		
		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.first()) {
			return rs.getString(1);
		}
		throw new IdNotExists(id);
		
		
	}
}
