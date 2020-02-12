package com.alex.forums.comments;

import java.sql.Date;

public class Comment {
	
	public Comment(long id, String creator, long creatorID, Date creationDate,
			String content, long likes, long dislikes,
			boolean isDeleted, boolean isLocked, long parentThread,
			long parentPost, long parentComment) {
		this.id = id;
		this.creator = creator;
		this.creatorID = creatorID;
		this.creationDate = creationDate;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.isDeleted = isDeleted;
		this.isLocked = isLocked;
		this.parentThread = parentThread;
		this.parentPost = parentPost;
		this.parentComment = parentComment;
	}
	
	public final long id;
	public final String creator;
	public final long creatorID;
	public final Date creationDate;
	public final String content;
	public final long likes;
	public final long dislikes;
	public final boolean isDeleted;
	public final boolean isLocked;
	public final long parentThread;
	public final long parentPost;
	public final long parentComment;
	
	//get like dislike ratio
	public double getLDRatio() {
		double percent = 0.0;
		percent = (double) this.dislikes / (double) this.likes;
		return percent;
	}
	

}
