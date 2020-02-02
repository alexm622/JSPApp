package com.alex.forums.posts.post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alex.forums.posts.Post;

public class PostPage {
	
	public static String mainPost(long id, long threadID) {
		
		
		
		return null;
	}
	
	private static Post getPost(long id, long threadID) {
		String sql = "SELECT * FROM forums.Posts WHERE parentThread=? AND id=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the parameters
		stmt.setLong(1, threadID);
		stmt.setLong(2, id);
		
		//execute the query
		ResultSet rs = stmt.executeQuery();
		
		Post p = new Post(
				rs.getLong(1), //id
				rs.getString(2), //title
				rs.getString(3), //creator
				rs.getLong(4), //creatorID
				rs.getDate(5), //creationDate
				rs.getString(6), //content
				rs.getLong(7), // likes
				rs.getLong(8), //dislikes
				rs.getBoolean(9), 
				rs.getBoolean(10),
				rs.getBoolean(11),
				rs.getLong(12)
				); //get isLocked
		
	}
	
	
}
