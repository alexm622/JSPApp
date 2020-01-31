package com.alex.forums.posts;

import java.sql.Date;

public class Post {
	
	public Post(long id, String title, String creator, long creatorID, Date creationDate,
			String content, long likes, long dislikes,
			boolean isDeleted, boolean isArchived,boolean isLocked, long parentThread) {
		this.creationDate = creationDate;
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.creatorID = creatorID;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.isDeleted = isDeleted;
		this.isArchived = isArchived;
		this.isLocked = isLocked;
		this.parentThread = parentThread;
		
	}
	
	public final long id;
	public final String title;
	public final String creator;
	public final long creatorID;
	public final Date creationDate;
	public final String content;
	public final long likes;
	public final long dislikes;
	public final boolean isDeleted;
	public final boolean isArchived;
	public final boolean isLocked;
	public final long parentThread;
	
	//get like dislike ratio
	public double getLDRatio() {
		double percent = 0.0;
		percent = (double) this.dislikes / (double) this.likes;
		return percent;
	}
	

}
