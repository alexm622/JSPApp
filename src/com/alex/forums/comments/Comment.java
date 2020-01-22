package com.alex.forums.comments;

public class Comment {
	
	public Comment(long id, String creator, long creatorID,
			String content, long likes, long dislikes,
			boolean isDeleted, long parentThread, long parentComment,
			boolean isLocked) {
		this.id = id;
		this.creator = creator;
		this.creatorID = creatorID;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.isDeleted = isDeleted;
		this.isLocked = isLocked;
		this.parentThread = parentThread;
		this.parentComment = parentComment;
	}
	
	public final long id;
	public final String creator;
	public final long creatorID;
	public final String content;
	public final long likes;
	public final long dislikes;
	public final boolean isDeleted;
	public final boolean isLocked;
	public final long parentThread;
	public final long parentComment;
	
	//get like dislike ratio
	public double getLDRatio() {
		double percent = 0.0;
		percent = (double) this.dislikes / (double) this.likes;
		return percent;
	}
	

}
