package com.alex.forums.posts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alex.forums.users.utils.UserUtils;
import com.alex.utils.exceptions.IdNotExists;
import com.alex.utils.sql.SQLConnect;

public class PostUtils {
	
	private static ArrayList<Post> getPosts(int offset, Connection con, long parent) throws ClassNotFoundException, IOException, SQLException{
		
		//arraylist of posts
		ArrayList<Post> posts = new ArrayList<>();
		
		//fetch 20 items from table with offset offset
		String sql = "SELECT * FROM forums.Posts WHERE parentThread=? LIMIT 20 OFFSET ?";
		
		//get the prepare statement
		PreparedStatement stmt = con.prepareStatement(sql);
		
		//set the parameters
		stmt.setLong(1, parent);
		stmt.setInt(2, offset);
		
		//execute the query
		ResultSet rs = stmt.executeQuery();
		
		//iterate through the rows
		/* id, title,  creator,  creatorID, creationDate, content,  likes,
		 *   dislikes, isDeleted, isArchived,
		 *    isLocked, parentThread*/
		while(rs.next()) {
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
			
			//add the post
			posts.add(p);
			
		}
		return posts;
	}
	
	public static String parseToDivs(int offset, long parent) throws ClassNotFoundException, IOException, SQLException, IdNotExists {
		
		System.out.println("Parent = " + parent);
		
		//output string
		String out = "";
		
		//stringbuilder for final output
		StringBuilder sb = new StringBuilder();
		
		//get connection
		Connection con = SQLConnect.getCon("forums", "server", "serverpass");
		
		//arraylist of posts
		ArrayList<Post> posts = getPosts(offset, con, parent);
		
		//html elements
		final String div = " <div class=\"status-entry color6\"> ? </div>";
		final String row = "<tr> ? </tr>";
		final String column = "<th> ? <th>";
		final String table = "<table> ? </table>";
		final String button = "<button class=\"link\" name=\"submit\" type=\"submit\" value=\"?\"> ? </button>";
		
		for(Post p : posts) {
			out = div.replace("?", makeForm(p)).replace("?", table);
			
			//make an arraylist containing all of the columns
			ArrayList<String> columns = new ArrayList<String>();
			
			//add columns
			columns.add(column.replace("?", makeLink(p)));
			columns.add(column.replace("?", "Creator: " + UserUtils.idToDisplayName(p.id, con)));
			
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
		return out.replace("?", sb.toString());
	}
	
	private static String makeForm(Post p) {
		final String form = "<form name=\"!\" method=\"POST\" action=\"PostServlet\">"
				+ "<input name=\"id\" type=\"hidden\" value=\"!\"/>"
				+ "?"
				+ "</form>";
		final String input1 = "<input name=\"postID\" type=\"hidden\" value=\"!\" /> ?";
		
		
		
		
		
		//get the post id
		long id = p.id;
		
		//replace the values
		String out = form.replaceAll("!", (new Long(id).toString()));
		out = out.replace("?" , input1);
		out = out.replace("!", (new Long(p.parentThread)).toString());
		
		//return the forms
		return out;	
	}
	
	private static String makeLink(Post p) {
		//the html link element
		final String link = "<a onclick=\"this.closest('form').submit();return false;\">?</a>";
		
		//fetch the variables from PostServlet
		long id = p.id;
		String name = p.title;
		
		//make the link
		String out = (link.replaceAll("!", (new Long(id).toString()))).replace("?", name);
		
		return out;
		
	}
	
	
}
