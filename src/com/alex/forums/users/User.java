package com.alex.forums.users;

import java.util.Date;

public class User {
	
	public User(String username, long id,
			Date creationDate, long posts, long comments, long likes,
			long dislikes, String usernameHash,
			String passwordHash) {
		this.username = username;
		this.id = id;
		this.creationDate = creationDate;
		this.posts = posts;
		this.comments = comments;
		this.likes = likes;
		this.dislikes = dislikes;
		this.usernameHash = usernameHash;
		this.passwordHash = passwordHash;
		
		
		
	}
	
	public final String username;
	public final long id;
	public final Date creationDate;
	public final long posts;
	public final long comments;
	public final long likes;
	public final long dislikes;
	public final String usernameHash;
	public final String passwordHash;
	
}
