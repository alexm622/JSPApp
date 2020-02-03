package com.alex.forums.posts.post;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alex.forums.comments.Comment;
import com.alex.forums.posts.Post;
import com.alex.forums.users.utils.UserUtils;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.sql.SQLConnect;

public class PostPage {
	
	private static String br = "<br/>";
	
	//max comments to fetch
	private static final int COUNT = 20;
	
	//mac subcomments to fetch
	private static final int MAX_SUBCOMMENTS = 5;
	
	
	public static String mainPost(long id, long threadID) throws ClassNotFoundException, IOException, SQLException, IdNotExists {
		
		Post p = getPost(id, threadID);
		
		String out = makePage(p);
		
		
		return out;
	}
	
	private static Post getPost(long id, long threadID) throws ClassNotFoundException, IOException, SQLException {
		System.out.println("the id is " + id);
		
		System.out.println("the parent thread is " + threadID);
		
		
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		String sql = "SELECT * FROM forums.Posts WHERE parentThread=? AND id=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the parameters
		stmt.setLong(1, threadID);
		stmt.setLong(2, id);
		
		//execute the query
		ResultSet rs = stmt.executeQuery();
		
		rs.first();
		
		
		Post p = new Post(
				rs.getLong(1), //id
				rs.getString(2), //title
				rs.getString(3), //creator
				rs.getLong(4), //creatorID
				rs.getDate(5), //creationDate
				rs.getString(6), //content
				rs.getLong(7), // likes
				rs.getLong(8), //dislikes
				rs.getBoolean(9), //get isDeleted
				rs.getBoolean(10), //get isLocked
				rs.getBoolean(11), //get isArchived
				rs.getLong(12) //get parent thread
				); 
		
		System.out.println("post name is " + p.title);
		
		con.close();
		return p;
		
	}
	
	private static String makePage(Post p) throws ClassNotFoundException, IdNotExists, SQLException, IOException {
		
		String post = makePost(p);
		String comments = makeComments(p);
		
		String out = post + br + comments;
		
		return out;
	}
	
	private static String makePost(Post p) throws IdNotExists, SQLException, ClassNotFoundException, IOException {
		
		final String div = " <div class=\"status-entry color6\"> ? </div>";
		
		String content = p.content;
		
		final String sub_div = "<div class=\"status-entry color7\"> ! </div> <br/> ? ";
		
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		String post_info = "Made by: " + UserUtils.idToDisplayName(p.id, con) + " creation date: " + p.creationDate.toString(); 
		con.close();
		
		String sub = sub_div.replace("!", post_info);
		
		String out = div.replace("?", sub);
		
		out = out.replace("?", content);
		
		
		
		return out;
	}
	
	private static String makeComments(Post p) {
		// TODO make this
		
		ArrayList<Comment> comments = getComments(p);
		
		
		
		return "";
	}
	
	private static ArrayList<Comment> getComments(Post p){
		
		
		
		
		return null;
	}
	
	
	
}
